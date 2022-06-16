package com.sue.coroutine_searchimage.domain.model

data class ImageModel(
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeHeight: Int,
    val sizeWidth: Int,
    val isFavorite: Boolean = false
)
