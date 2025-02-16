package com.example.foodist.presentation.di

import com.example.foodist.domain.usecase.FoodInteractor
import com.example.foodist.domain.usecase.FoodUseCase
import com.example.foodist.presentation.favorite.FavoriteViewModel
import com.example.foodist.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module{
    factory<FoodUseCase> { FoodInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }

}