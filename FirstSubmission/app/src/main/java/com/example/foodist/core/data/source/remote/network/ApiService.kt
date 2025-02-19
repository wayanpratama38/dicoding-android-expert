package com.example.foodist.core.data.source.remote.network

import com.example.foodist.core.data.source.remote.response.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php")
    suspend fun getAllMeals(
        @Query ("c") category : String = "Seafood"
    ): DetailResponse

    @GET("lookup.php")
    suspend fun getDetailMeal(
        @Query("i") id : String
    ): DetailResponse

}