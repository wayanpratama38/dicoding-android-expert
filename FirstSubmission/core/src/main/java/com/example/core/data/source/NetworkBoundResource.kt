package com.example.core.data.source

import com.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType,RequestType> {

    private var result : Flow<Resource<ResultType>> = flow{
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if(shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data!!)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty ->{
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    //
    protected open fun onFetchFailed() {}

    // Untuk load data dari database
    protected abstract fun loadFromDB() : Flow<ResultType>

    // Untuk mengembalikan kondisi ketika mengambil database
    protected abstract fun shouldFetch(data : ResultType?) : Boolean

    // Untuk memanggil dari API
    protected abstract suspend fun createCall() : Flow<ApiResponse<RequestType>>

    // Untuk save hasil pemanggilan API
    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow() : Flow<Resource<ResultType>> = result

}