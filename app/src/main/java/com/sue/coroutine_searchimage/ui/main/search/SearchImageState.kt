package com.sue.coroutine_searchimage.ui.main.search

import androidx.paging.PagingData
import com.sue.coroutine_searchimage.domain.model.Image

sealed class SearchImageState{
    data class Success(
        val images: PagingData<Image>
    ): SearchImageState()
}
