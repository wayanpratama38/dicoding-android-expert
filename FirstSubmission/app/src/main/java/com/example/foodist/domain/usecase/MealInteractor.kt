package com.example.foodist.domain.usecase

import com.example.foodist.data.source.Resource
import com.example.foodist.domain.model.Meal
import com.example.foodist.domain.repository.IMealRepository
import kotlinx.coroutines.flow.Flow

class MealInteractor(private val mealRepository: IMealRepository) : MealUseCase
{
    override fun getAllMeal(): Flow<Resource<List<Meal>>> = mealRepository.getAllMeals()

    override fun getAllFavoriteMeal(): Flow<List<Meal>>  = mealRepository.getAllFavoriteMeal()

    override fun getDetailMeal(id: String): Flow<Resource<Meal>> = mealRepository.getDetailMeal(id)

    override fun setFavoriteMeal(meal: Meal, state: Boolean) = mealRepository.setFavoriteMeal(meal,state)
}