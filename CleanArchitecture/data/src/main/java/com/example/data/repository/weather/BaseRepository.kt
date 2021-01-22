package com.example.data.repository.weather

import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.utils.Connectivity
import com.example.data.database.DB_ENTRY_ERROR
import com.example.data.networking.base.DomainMapper
import com.example.domain.model.Failure
import com.example.domain.model.HttpError
import com.example.domain.model.Result
import com.example.domain.model.Success
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException

@KoinApiExtension
class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val coroutineContext: CoroutineContextProvider by inject()
    private val connectivity: Connectivity by inject()

    private suspend fun fetchData(
        apiProvider: () -> Result<T>,
        dbProvider: () -> R?
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(coroutineContext.io) {
                apiProvider()
            }
        } else {
            withContext(coroutineContext.io) {
                val dbResult = dbProvider()
                if (dbResult != null)
                    Success(dbResult.mapToDomainEntity())
                else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */

    private suspend fun fetchData(
        dbProvider: () -> R?
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(coroutineContext.io) {
                val dbResult = dbProvider()
                if (dbResult != null)
                    Success(dbResult.mapToDomainEntity())
                else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        } else Return
    }
}


