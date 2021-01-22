package com.example.cleanarchitecture.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.common.extensions.launch
import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.utils.Connectivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {
    protected val coroutineContext: CoroutineContextProvider by inject()
    protected val connectivity: Connectivity by inject()

    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        _viewState.value = Loading()
        if (connectivity.hasNetworkAccess()) {
            launch { action() }
        } else {
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        _viewState.value = Loading()
        launch { action() }
    }
}