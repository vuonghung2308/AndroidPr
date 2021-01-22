package com.example.data.networking.base

import com.example.data.common.mapper.DomainMapper
import com.example.data.common.mapper.RoomMapper
import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.domain.model.Error
import com.example.domain.model.Failure
import com.example.domain.model.Result
import com.example.domain.model.Success
import retrofit2.Response
import java.io.IOException

inline fun <T : Any> Response<T>.onSuccess(
    action: (T) -> Unit
): Response<T> {
    if (isSuccessful)
        body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(
    action: (Error) -> Unit
) {
    if (!isSuccessful)
        errorBody()?.run { action(Error(Throwable(message()), code())) }
}

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCache: () -> R?
): Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.mapToDomainEntity())
        }
        onFailure {
            val databaseEntity = fetchFromCache()
            if (databaseEntity != null)
                return Success(databaseEntity.mapToDomainEntity())
            else return Failure(it)
        }
        return Failure(Error(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(Error(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    try {
        onSuccess { return Success(it.mapToDomainEntity()) }
        onFailure { return Failure(it) }
        return Failure(Error(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(Error(Throwable(GENERAL_NETWORK_ERROR)))
    }
}