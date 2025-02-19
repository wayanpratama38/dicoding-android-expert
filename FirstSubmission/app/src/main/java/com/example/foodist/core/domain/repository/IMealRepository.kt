package com.example.foodist.core.domain.repository

import com.example.foodist.core.data.source.Resource
import com.example.foodist.core.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface IMealRepository {
    fun getAllMeals() : Flow<Resource<List<Meal>>>

    fun getAllFavoriteMeal() : Flow<List<Meal>>

    fun getDetailMeal(id : String) : Flow<Resource<Meal>>

    fun setFavoriteMeal(meal : Meal, state : Boolean)
}