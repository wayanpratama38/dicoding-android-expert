package com.example.foodist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MealUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class HomeViewModel(private val mealUseCase: MealUseCase): ViewModel() {

    private val _query = MutableStateFlow("")
    private val query : StateFlow<String> get() = _query

    val meal = mealUseCase.getAllMeal().asLiveData()

    val searchMeal = query.flatMapLatest { query ->
        mealUseCase.searchMeal(query)
    }.asLiveData()

    fun searchMeal(newQuery : String){
        _query.value = newQuery
    }
}