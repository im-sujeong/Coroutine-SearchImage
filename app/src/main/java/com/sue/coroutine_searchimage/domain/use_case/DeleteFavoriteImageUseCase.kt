package com.sue.coroutine_searchimage.domain.use_case

import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.repository.ImageRepository

class DeleteFavoriteImageUseCase(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(image: Image) {
        imageRepository.deleteFavoriteImage(image.toImageEntity())
    }
}