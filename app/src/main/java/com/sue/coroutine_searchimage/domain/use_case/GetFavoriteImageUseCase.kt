package com.sue.coroutine_searchimage.domain.use_case

import com.sue.coroutine_searchimage.data.mapper.toImageModels
import com.sue.coroutine_searchimage.domain.model.ImageModel
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteImageUseCase(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(): Flow<List<ImageModel>> {
        return imageRepository.getFavoriteImages().map {
            it.toImageModels()
        }
    }
}