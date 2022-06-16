package com.sue.coroutine_searchimage.domain.repository

import androidx.paging.PagingData
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity
import com.sue.coroutine_searchimage.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getFavoriteImages(): Flow<List<ImageEntity>>

    suspend fun searchImages(query: String): Flow<PagingData<ImageModel>>

    suspend fun insertFavoriteImage(imageEntity: ImageEntity)

    suspend fun deleteFavoriteImage(imageEntity: ImageEntity)
}