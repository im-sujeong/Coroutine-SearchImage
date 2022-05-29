package com.sue.coroutine_searchimage.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sue.coroutine_searchimage.domain.model.Image

@Entity
data class ImageEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeHeight: Int,
    val sizeWidth: Int
) {
    fun toImage(): Image =
        Image(
            title = title,
            link = link,
            thumbnail = thumbnail,
            sizeHeight = sizeHeight,
            sizeWidth = sizeWidth
        )
}