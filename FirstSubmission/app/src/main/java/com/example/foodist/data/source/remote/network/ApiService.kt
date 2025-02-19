package com.example.foodist.data.source.remote.network

import com.example.foodist.data.source.remote.response.FoodDetailResponse
import com.example.foodist.data.source.remote.response.FoodResponse
import com.example.foodist.data.source.remote.response.FoodResponseItem
import com.example.foodist.data.source.remote.response.MealItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @GET("/")
//    suspend fun getAllFood(
//        @Header("x-rapidapi-key") apiKey : String,
//        @Header("x-rapidapi-host") apiHost : String,
//    ) : List<FoodResponseItem>
//
//    @GET("/{id}")
//    suspend fun getDetailFood(
//        @Path("id") id : String,
//        @Header("x-rapidapi-key") apiKey : String,
//        @Header("x-rapidapi-host") apiHost : String,
//    ) : FoodDetailResponse

    @GET("/filter.php")
    suspend fun getAllMeals(
        @Query ("c") category : String = "Seafood"
    ):List<MealItem>

    @GET("/lookup.php")
    suspend fun getDetailMeal(
        @Query("i") id : String
    ):MealItem

}