package com.example.foodist.domain.repository

import com.example.foodist.data.source.Resource
import com.example.foodist.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface IMealRepository {
    fun getAllMeals() : Flow<Resource<List<Meal>>>

    fun getAllFavoriteMeal() : Flow<List<Meal>>

    fun getDetailMeal(id : String) : Flow<Resource<Meal>>

    fun setFavoriteMeal(meal : Meal, state : Boolean)
}