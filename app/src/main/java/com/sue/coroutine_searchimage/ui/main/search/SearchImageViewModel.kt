package com.sue.coroutine_searchimage.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.use_case.UseCases
import com.sue.coroutine_searchimage.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val useCases: UseCases
): BaseViewModel() {
    private val _state = MutableLiveData<SearchImageState>()
    val state : LiveData<SearchImageState> = _state

    private val queryFlow = MutableSharedFlow<String>()

    override fun fetchData() = viewModelScope.launch{
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

    fun handleQuery(query: String) = viewModelScope.launch(errorHandler) {
        queryFlow.emit(query)
    }

    fun favoriteImage(image: Image) = viewModelScope.launch(errorHandler){
        useCases.insertFavoriteImageUseCase(image)
    }

    private fun setState(state: SearchImageState) {
        _state.value = state
    }
}