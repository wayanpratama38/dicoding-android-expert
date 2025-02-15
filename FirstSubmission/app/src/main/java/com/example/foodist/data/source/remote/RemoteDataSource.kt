package com.example.foodist.data.source.remote

import android.util.Log
import com.example.foodist.BuildConfig
import com.example.foodist.data.source.remote.network.ApiResponse
import com.example.foodist.data.source.remote.network.ApiService
import com.example.foodist.data.source.remote.response.FoodDetailResponse
import com.example.foodist.data.source.remote.response.FoodResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    private val apiKey = BuildConfig.API_KEY
    private val apiHost = BuildConfig.API_HOST

    suspend fun getAllFood(): Flow<ApiResponse<List<FoodResponseItem>>> {
        return flow{
            try {
                val response =apiService.getAllFood(apiKey = apiKey,apiHost = apiHost)
                val data = response.foodResponse?.filterNotNull()
                if(!data.isNullOrEmpty()){
                    emit(ApiResponse.Success(data))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG,e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailFood(id : String) : Flow<ApiResponse<FoodDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailFood(id = id , apiKey = apiKey , apiHost = apiHost)
                emit(ApiResponse.Success(response))
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG,e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        const val TAG = "RemoteDataSource"
    }
}