package com.sue.coroutine_searchimage.data.mapper

import com.sue.coroutine_searchimage.data.db.entity.ImageEntity
import com.sue.coroutine_searchimage.data.network.dto.ImageDto
import com.sue.coroutine_searchimage.domain.model.ImageModel

fun ImageEntity.toImageModel() : ImageModel =
    ImageModel(
        title = title,
        link = link,
        thumbnail = thumbnail,
        sizeHeight = sizeHeight,
        sizeWidth = sizeWidth
    )

fun List<ImageEntity>.toImageModels(): List<ImageModel> =
    map {
        it.toImageModel()
    }

fun ImageDto.toImageModel(): ImageModel =
    ImageModel(
        title = title,
        link = link,
        thumbnail = thumbnail,
        sizeHeight = sizeHeight,
        sizeWidth = sizeWidth
    )

fun ImageModel.toImageEntity(): ImageEntity =
    ImageEntity(
        title = title,
        link = link,
        thumbnail = thumbnail,
        sizeHeight = sizeHeight,
        sizeWidth = sizeWidth
    )