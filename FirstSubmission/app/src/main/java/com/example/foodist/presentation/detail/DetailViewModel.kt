package com.example.foodist.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodist.core.domain.model.Meal
import com.example.foodist.core.domain.usecase.MealUseCase

class DetailViewModel(private val mealUseCase: MealUseCase, mealId : String): ViewModel() {
    fun setFavoriteFood(meal : Meal, newStatus : Boolean) = mealUseCase.setFavoriteMeal(meal,newStatus)

    val meal  = mealUseCase.getDetailMeal(mealId).asLiveData()
}