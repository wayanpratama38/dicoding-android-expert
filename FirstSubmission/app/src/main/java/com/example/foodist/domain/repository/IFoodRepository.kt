package com.example.foodist.domain.repository

import com.example.foodist.data.source.Resource
import com.example.foodist.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {
    fun getAllFood() : Flow<Resource<List<Food>>>

    fun getAllFavoriteFood() : Flow<List<Food>>

    fun getDetailFood(id : String) : Flow<Resource<Food>>

    fun setFavoriteFood(food : Food)
}