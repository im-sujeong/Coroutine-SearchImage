package com.sue.coroutine_searchimage.ui.main.favorite

import com.sue.coroutine_searchimage.domain.model.Image

sealed class FavoriteImageState {
    data class Success(
        val images: List<Image>
    ): FavoriteImageState()
}