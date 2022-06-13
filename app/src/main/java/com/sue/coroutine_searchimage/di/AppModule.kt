package com.sue.coroutine_searchimage.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.sue.coroutine_searchimage.BuildConfig
import com.sue.coroutine_searchimage.data.db.ImageDatabase
import com.sue.coroutine_searchimage.data.network.SearchApi
import com.sue.coroutine_searchimage.data.network.url.Url.BASE_URL
import com.sue.coroutine_searchimage.data.repository.ImageRepositoryImpl
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import com.sue.coroutine_searchimage.domain.use_case.GetFavoriteImageUseCase
import com.sue.coroutine_searchimage.domain.use_case.InsertFavoriteImageUseCase
import com.sue.coroutine_searchimage.domain.use_case.SearchImageUseCase
import com.sue.coroutine_searchimage.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideImageRepository(
        searchApi: SearchApi,
        db: ImageDatabase
    ): ImageRepository {
        return ImageRepositoryImpl(
            Dispatchers.IO,
            searchApi,
            db.imageDao
        )
    }

    @Provides
    @Singleton
    fun provideImageDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(
            app, ImageDatabase::class.java, "image_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSearchApi(
        retrofit: Retrofit
    ): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_API_CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_API_CLIENT_SECRET)
                    .build()

                chain.proceed(request)
            }
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideUseCases(imageRepository: ImageRepository): UseCases {
        return UseCases(
            searchImageUseCase = SearchImageUseCase(imageRepository),
            insertFavoriteImageUseCase = InsertFavoriteImageUseCase(imageRepository),
            getFavoriteImageUseCase = GetFavoriteImageUseCase(imageRepository)
        )
    }
}