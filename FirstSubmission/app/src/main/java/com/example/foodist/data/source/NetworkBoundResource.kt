package com.example.foodist.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType,RequestType> {

    private var result : Flow<Resource<ResultType>> = flow{
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()

    }

    // Untuk load data dari database
    protected abstract fun loadFromDB() : Flow<ResultType>

    // Untuk mengembalikan kondisi ketika mengambil database
    protected abstract fun shouldFetch(data : ResultType?) : Boolean
}