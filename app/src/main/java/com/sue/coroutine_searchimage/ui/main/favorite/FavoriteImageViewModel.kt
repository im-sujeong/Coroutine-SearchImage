package com.sue.coroutine_searchimage.ui.main.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteImageViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _state = MutableLiveData<FavoriteImageState>()
    val state: LiveData<FavoriteImageState> = _state

    fun fetchData() {
        Log.e("sujeong", "fetchData()")

        useCases.getFavoriteImageUseCase()
            .onEach {
                setState(FavoriteImageState.Success(it))
            }
            .launchIn(viewModelScope)
    }

    fun deleteFavoriteImage(image: Image) = viewModelScope.launch{
        useCases.deleteFavoriteImageUseCase(image)
    }

    private fun setState(state: FavoriteImageState) {
        _state.value = state
    }
}