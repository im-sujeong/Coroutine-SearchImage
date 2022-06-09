package com.sue.coroutine_searchimage.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.use_case.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase
): ViewModel() {
    private val queryFlow = MutableSharedFlow<String>()

    val pagingDataFlow = queryFlow
        .flatMapLatest {
            searchImage(it)
        }
        .cachedIn(viewModelScope)

    //SharedFlow : Hot Flow
    //Cold Flow
    suspend fun searchImage(query: String): Flow<PagingData<Image>> = searchImageUseCase(query)

    fun handleQuery(query: String) {
        viewModelScope.launch {
            queryFlow.emit(query)
        }
    }
}