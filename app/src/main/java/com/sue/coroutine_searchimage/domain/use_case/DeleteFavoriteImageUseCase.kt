package com.sue.coroutine_searchimage.domain.use_case

import com.sue.coroutine_searchimage.data.mapper.toImageEntity
import com.sue.coroutine_searchimage.domain.model.ImageModel
import com.sue.coroutine_searchimage.domain.repository.ImageRepository

class DeleteFavoriteImageUseCase(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(imageModel: ImageModel) {
        imageRepository.deleteFavoriteImage(imageModel.toImageEntity())
    }
}