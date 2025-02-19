package com.example.foodist.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MealUseCase

class FavoriteViewModel(mealUseCase: MealUseCase) : ViewModel() {

    val meal = mealUseCase.getAllFavoriteMeal().asLiveData()
}