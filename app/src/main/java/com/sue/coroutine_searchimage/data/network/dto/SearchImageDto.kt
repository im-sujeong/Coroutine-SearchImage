package com.sue.coroutine_searchimage.data.network.dto

data class SearchImageDto(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<ImageDto>
): BaseDto()