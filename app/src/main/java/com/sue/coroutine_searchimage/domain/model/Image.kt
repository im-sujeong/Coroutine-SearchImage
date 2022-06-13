package com.sue.coroutine_searchimage.domain.model

import com.google.gson.annotations.SerializedName
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity

data class Image(
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeHeight: Int,
    val sizeWidth: Int
) {
    fun toImageEntity(): ImageEntity =
        ImageEntity(
            title = title,
            link = link,
            thumbnail = thumbnail,
            sizeHeight = sizeHeight,
            sizeWidth = sizeWidth
        )
}
