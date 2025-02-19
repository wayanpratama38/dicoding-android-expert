package com.example.foodist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodist.core.domain.usecase.MealUseCase

class HomeViewModel(mealUseCase: MealUseCase): ViewModel() {

    val meal = mealUseCase.getAllMeal().asLiveData()
}