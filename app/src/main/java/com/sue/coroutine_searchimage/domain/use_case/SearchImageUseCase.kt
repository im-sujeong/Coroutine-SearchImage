package com.sue.coroutine_searchimage.domain.use_case

import androidx.paging.PagingData
import com.sue.coroutine_searchimage.domain.model.ImageModel
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class SearchImageUseCase(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<ImageModel>> {
        return imageRepository.searchImages(query)
    }
}