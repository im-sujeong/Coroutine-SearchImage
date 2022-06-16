package com.sue.coroutine_searchimage.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable -> }

    open fun fetchData() = viewModelScope.launch {

    }
}