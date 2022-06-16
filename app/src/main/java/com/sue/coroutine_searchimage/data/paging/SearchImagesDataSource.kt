package com.sue.coroutine_searchimage.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sue.coroutine_searchimage.data.mapper.toImageModel
import com.sue.coroutine_searchimage.data.network.SearchApi
import com.sue.coroutine_searchimage.domain.model.ImageModel
import java.lang.Exception

class SearchImagesDataSource(
    private val query: String,
    private val searchApi: SearchApi
): PagingSource<Int, ImageModel>() {
    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(defaultDisplay) ?: anchorPage?.nextKey?.minus(defaultDisplay)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        val start = params.key ?: defaultStart

        return try {
            val response = searchApi.searchImages(
                query = query,
                display = params.loadSize,
                start = start
            )

            val items = response.items.map {
                it.toImageModel()
            }

            val nextKey = if( items.isEmpty() ) {
                null
            }else {
                start + params.loadSize
            }

            val prevKey = if(start == defaultStart) {
                null
            }else {
                start - defaultDisplay
            }

            LoadResult.Page(items, prevKey, nextKey)
        }catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val defaultStart = 1
        const val defaultDisplay = 10
    }
}