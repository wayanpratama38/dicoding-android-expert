package com.example.foodist.presentation.di

import com.example.foodist.core.domain.usecase.MealInteractor
import com.example.foodist.core.domain.usecase.MealUseCase
import com.example.foodist.presentation.detail.DetailViewModel
import com.example.foodist.presentation.favorite.FavoriteViewModel
import com.example.foodist.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module{
    factory<MealUseCase> { MealInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { (mealId : String) ->
        DetailViewModel(get(),mealId)
    }

}