package com.sue.coroutine_searchimage.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sue.coroutine_searchimage.data.db.dao.ImageDao
import com.sue.coroutine_searchimage.data.network.SearchApi
import com.sue.coroutine_searchimage.data.paging.SearchImagesDataSource
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(
    private val searchApi: SearchApi,
    private val imageDao: ImageDao
): ImageRepository {
    override suspend fun searchImages(query: String): Flow<PagingData<Image>> {
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
}