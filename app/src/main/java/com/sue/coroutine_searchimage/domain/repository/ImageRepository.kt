package com.sue.coroutine_searchimage.domain.repository

import androidx.paging.PagingData
import com.sue.coroutine_searchimage.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun searchImages(query: String): Flow<PagingData<Image>>
}