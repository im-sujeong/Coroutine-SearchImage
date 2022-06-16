package com.sue.coroutine_searchimage.ui.main.favorite

import com.sue.coroutine_searchimage.domain.model.ImageModel

sealed class FavoriteImageState {
    data class Success(
        val imageModels: List<ImageModel>
    ): FavoriteImageState()
}