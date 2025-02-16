package com.example.foodist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodist.domain.usecase.FoodUseCase

class HomeViewModel(foodUseCase: FoodUseCase): ViewModel() {

    val food = foodUseCase.getAllFood().asLiveData()
}