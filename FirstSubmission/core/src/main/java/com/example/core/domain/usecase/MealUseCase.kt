package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealUseCase {
    fun getAllMeal() : Flow<Resource<List<Meal>>>

    fun getAllFavoriteMeal(): Flow<List<Meal>>

    fun getDetailMeal(id : String) : Flow<Resource<Meal>>

    fun setFavoriteMeal(meal : Meal, state : Boolean)

    fun searchMeal(query : String) : Flow<List<Meal>>
}