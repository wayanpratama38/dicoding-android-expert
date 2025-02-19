package com.example.foodist.data.source.remote

import android.util.Log
import com.example.foodist.BuildConfig
import com.example.foodist.data.source.remote.network.ApiResponse
import com.example.foodist.data.source.remote.network.ApiService
import com.example.foodist.data.source.remote.response.MealItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMeals(): Flow<ApiResponse<List<MealItem>>> {
        return flow{
            try {
                val response : List<MealItem> = apiService.getAllMeals()
//                val data = response.foodResponse?.filterNotNull()
                Log.d("RemoteDataSource","$response")
                if(response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG,e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMeal(id : String) : Flow<ApiResponse<MealItem>> {
        return flow {
            try {
                val response = apiService.getDetailMeal(id)
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