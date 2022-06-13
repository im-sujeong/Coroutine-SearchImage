package com.sue.coroutine_searchimage.domain.use_case

import com.sue.coroutine_searchimage.data.db.entity.toImages
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteImageUseCase(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(): Flow<List<Image>> {
        return imageRepository.getFavoriteImages().map {
            it.toImages()
        }
    }
}