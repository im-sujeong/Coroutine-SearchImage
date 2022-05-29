package com.sue.coroutine_searchimage.domain.model

import com.google.gson.annotations.SerializedName

data class Image(
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeHeight: Int,
    val sizeWidth: Int
)
