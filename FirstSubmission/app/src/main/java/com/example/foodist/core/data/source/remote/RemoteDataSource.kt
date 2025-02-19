package com.example.foodist.core.data.source.remote

import android.util.Log
import com.example.foodist.core.data.source.remote.network.ApiResponse
import com.example.foodist.core.data.source.remote.network.ApiService
import com.example.foodist.core.data.source.remote.response.MealItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMeals(): Flow<ApiResponse<List<MealItem>>> {
        Log.d("RemoteDataSource", "getAllMeals() called in RemoteDataSource")
        return flow{
            try {
                val response = apiService.getAllMeals().meals
                Log.d("RemoteDataSource","${response}")
                if(response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                Log.d("RemoteDataSource"," Error ${e.message}")
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG,e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMeal(id : String) : Flow<ApiResponse<MealItem>> {
        return flow {
            try {
                val response = apiService.getDetailMeal(id).meals.first()
                Log.d("RemoteDataSource","${response}")
                emit(ApiResponse.Success(response))
            } catch (e : Exception){
                Log.d("RemoteDataSource"," Error ${e.message}")
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG,e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        const val TAG = "RemoteDataSource"
    }
}