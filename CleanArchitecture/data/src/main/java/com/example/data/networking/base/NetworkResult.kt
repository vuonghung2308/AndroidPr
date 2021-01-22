package com.example.data.networking.base

import com.example.data.database.DB_ENTRY_ERROR
import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.domain.model.Failure
import com.example.domain.model.Result
import com.example.domain.model.HttpError
import com.example.domain.model.Success
import retrofit2.Response
import java.io.IOException

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

interface DomainMapper<out T : Any> {
    fun mapToDomainEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(
    action: (T) -> Result<T>
): Response<T> {
    if (isSuccessful)
        body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(
    action: (HttpError) -> Result<T>
) {
    if (!isSuccessful)
        errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve somthing from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCache: () -> R?
): Result<U> {
    try {
        onSuccess {
            val roomEntity = it.mapToRoomEntity()
            cacheAction(roomEntity)
            return Success(roomEntity.mapToDomainEntity())
        }
        onFailure {
            val databaseResult = fetchFromCache()
            if (databaseResult != null)
                return Success(databaseResult.mapToDomainEntity())
            else return Failure(it)
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

/**
 * Use this when communicating only with the api service
 */

fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    try {
        onSuccess { return Success(it.mapToDomainEntity()) }
        onFailure { return Failure(it) }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}
