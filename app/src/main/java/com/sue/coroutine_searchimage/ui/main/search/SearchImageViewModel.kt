package com.sue.coroutine_searchimage.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.use_case.SearchImageUseCase
import com.sue.coroutine_searchimage.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _state = MutableLiveData<SearchImageState>()
    val state : LiveData<SearchImageState> = _state

    private val queryFlow = MutableSharedFlow<String>()

    fun fetchData() = viewModelScope.launch{
        queryFlow
            .flatMapLatest {
                searchImage(it)
            }
            .cachedIn(viewModelScope)
            .collectLatest {
                setState(SearchImageState.Success(it))
            }
    }

    private suspend fun searchImage(query: String): Flow<PagingData<Image>> = useCases.searchImageUseCase(query)

    fun handleQuery(query: String) = viewModelScope.launch {
        queryFlow.emit(query)
    }

    fun favoriteImage(image: Image) = viewModelScope.launch{
        useCases.insertFavoriteImageUseCase(image)
    }

    private fun setState(state: SearchImageState) {
        _state.value = state
    }
}