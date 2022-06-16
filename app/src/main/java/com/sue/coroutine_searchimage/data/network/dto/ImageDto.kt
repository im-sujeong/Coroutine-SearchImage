package com.sue.coroutine_searchimage.data.network.dto

import com.google.gson.annotations.SerializedName
import com.sue.coroutine_searchimage.domain.model.ImageModel

data class ImageDto(
    val title: String,
    val link: String,
    val thumbnail: String,
    @SerializedName("sizeheight") val sizeHeight: Int,
    @SerializedName("sizewidth") val sizeWidth: Int
)