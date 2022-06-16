package com.sue.coroutine_searchimage.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sue.coroutine_searchimage.data.db.dao.ImageDao
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity
import com.sue.coroutine_searchimage.data.network.SearchApi
import com.sue.coroutine_searchimage.data.paging.SearchImagesDataSource
import com.sue.coroutine_searchimage.domain.model.ImageModel
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ImageRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val searchApi: SearchApi,
    private val imageDao: ImageDao
): ImageRepository {
    override fun getFavoriteImages(): Flow<List<ImageEntity>> =
        imageDao.getImages().flowOn(ioDispatcher)

    override suspend fun searchImages(query: String): Flow<PagingData<ImageModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = SearchImagesDataSource.defaultDisplay,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchImagesDataSource(query, searchApi)
            }
        ).flow
    }

    override suspend fun insertFavoriteImage(imageEntity: ImageEntity) {
        imageDao.insertImage(imageEntity)
    }

    override suspend fun deleteFavoriteImage(imageEntity: ImageEntity) {
        imageDao.deleteImage(imageEntity)
    }
}