package com.example.openweather.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.utils.Connectivity
import com.example.domain.model.Result
import com.example.openweather.common.extension.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {
    private val connectivity: Connectivity by inject()
    private val coroutineContext: CoroutineContextProvider by inject()
    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState

    protected val _viewEffect = MutableLiveData<E>()
    val viewEffect: LiveData<E>
        get() = _viewEffect

    protected fun executeUseCase(
        action: suspend () -> Result<T>,
        noInternet: () -> Unit
    ) {
        _viewState.value = Loading()
        if (connectivity.hasNetworkAccess()) {
            launch { action() }
        } else noInternet()
    }

    protected fun executeUseCase(
        action: suspend () -> Unit
    ) {
        _viewState.value = Loading()
        launch { action() }
    }
}