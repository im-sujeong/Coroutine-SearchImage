package com.sue.coroutine_searchimage.domain.use_case

import androidx.paging.PagingData
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

//TODO cannot be provided without an @Inject constructor or an @Provides-annotated method.
class SearchImageUseCase(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<Image>> {
        return imageRepository.searchImages(query)
    }
}