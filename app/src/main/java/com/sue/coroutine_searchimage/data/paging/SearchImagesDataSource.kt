package com.sue.coroutine_searchimage.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sue.coroutine_searchimage.data.network.SearchApi
import com.sue.coroutine_searchimage.domain.model.Image
import java.lang.Exception

class SearchImagesDataSource(
    private val query: String,
    private val searchApi: SearchApi
): PagingSource<Int, Image>() {
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(defaultDisplay) ?: anchorPage?.nextKey?.minus(defaultDisplay)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val start = params.key ?: defaultStart

        return try {
            val response = searchApi.searchImages(
                query = query,
                display = params.loadSize,
                start = start
            )

            val items = response.items.map {
                it.toImage()
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