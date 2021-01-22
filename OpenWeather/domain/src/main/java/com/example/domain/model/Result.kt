package com.example.domain.model

sealed class Result<out T : Any> {
    fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success)
            action(data)
        return this
    }

    fun onFailure(action: (Error) -> Unit) {
        if (this is Failure)
            action(error)
    }
}

data class Success<out T : Any>(
    val data: T
) : Result<T>()

data class Failure(
    val error: Error
) : Result<Nothing>()

data class Error(
    val t: Throwable,
    val errorCode: Int = 0
)