package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MealItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

     fun getAllMeals(): Flow<ApiResponse<List<MealItem>>> {
        return flow{
            try {
                val response = apiService.getAllMeals().meals
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

     fun getDetailMeal(id : String) : Flow<ApiResponse<MealItem>> {
        return flow {
            try {
                val response = apiService.getDetailMeal(id).meals.first()
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