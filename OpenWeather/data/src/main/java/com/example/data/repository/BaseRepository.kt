package com.example.data.repository

import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.mapper.DomainMapper
import com.example.data.common.utils.Connectivity
import com.example.data.database.DB_ENTRY_ERROR
import com.example.domain.model.Error
import com.example.domain.model.Failure
import com.example.domain.model.Result
import com.example.domain.model.Success
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val connectivity: Connectivity by inject()
    private val coroutineContext: CoroutineContextProvider by inject()

    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R?
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(coroutineContext.io) {
                apiDataProvider()
            }
        } else {
            withContext(coroutineContext.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null)
                    Success(dbResult.mapToDomainEntity())
                else Failure(Error(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }
}