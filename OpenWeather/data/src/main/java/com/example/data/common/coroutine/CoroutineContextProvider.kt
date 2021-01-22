package com.example.data.common.coroutine

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider {
    val io: CoroutineContext by lazy { Dispatchers.IO }
    val main: CoroutineContext by lazy { Dispatchers.Main }
    val default: CoroutineContext by lazy { Dispatchers.Default }
}