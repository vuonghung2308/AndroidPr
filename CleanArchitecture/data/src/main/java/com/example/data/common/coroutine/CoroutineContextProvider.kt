package com.example.data.common.coroutine

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

/**
 * class for testing
 */

class TestCoroutineContextProvider : CoroutineContextProvider() {
    override val default: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
    override val main: CoroutineContext = Dispatchers.Unconfined
}
