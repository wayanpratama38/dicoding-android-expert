package com.example.foodist.domain.usecase

import com.example.foodist.data.source.Resource
import com.example.foodist.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    fun getAllFood() : Flow<Resource<List<Food>>>

    fun getAllFavoriteFood() : Flow<List<Food>>

    fun getDetailFood(id : String) : Flow<Resource<Food>>

    fun setFavoriteFood(food : Food,state : Boolean)

}