package com.example.foodist.data.source.remote.network

import com.example.foodist.data.source.remote.response.FoodDetailResponse
import com.example.foodist.data.source.remote.response.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("/")
    suspend fun getAllFood(
        @Header("x-rapidapi-key") apiKey : String,
        @Header("x-rapidapi-host") apiHost : String,
    ) : FoodResponse

    @GET("/{id}")
    suspend fun getDetailFood(
        @Path("id") id : String,
        @Header("x-rapidapi-key") apiKey : String,
        @Header("x-rapidapi-host") apiHost : String,
    ) : FoodDetailResponse

}