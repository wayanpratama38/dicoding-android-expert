package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.domain.model.Meal
import com.example.core.domain.repository.IMealRepository
import kotlinx.coroutines.flow.Flow

class MealInteractor(private val mealRepository: IMealRepository) : MealUseCase
{
    override fun getAllMeal(): Flow<Resource<List<Meal>>> = mealRepository.getAllMeals()

    override fun getAllFavoriteMeal(): Flow<List<Meal>>  = mealRepository.getAllFavoriteMeal()

    override fun getDetailMeal(id: String): Flow<Resource<Meal>> = mealRepository.getDetailMeal(id)

    override fun setFavoriteMeal(meal: Meal, state: Boolean) = mealRepository.setFavoriteMeal(meal,state)
}