package com.example.foodist.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodist.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    val food = foodUseCase.getAllFavoriteFood().asLiveData()
}