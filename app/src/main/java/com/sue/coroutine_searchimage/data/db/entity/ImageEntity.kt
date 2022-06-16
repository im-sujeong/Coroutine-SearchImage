package com.sue.coroutine_searchimage.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sue.coroutine_searchimage.domain.model.ImageModel

@Entity
data class ImageEntity(
    @PrimaryKey val title: String,
    val link: String,
    val thumbnail: String,
    val sizeHeight: Int,
    val sizeWidth: Int
)