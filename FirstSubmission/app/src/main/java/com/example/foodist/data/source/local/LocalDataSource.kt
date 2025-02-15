package com.example.foodist.data.source.local

import com.example.foodist.data.source.local.entity.FoodEntity
import com.example.foodist.data.source.local.room.FoodDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val foodDao : FoodDao) {

    fun getAllFood() : Flow<List<FoodEntity>> = foodDao.getAllFood()

    fun getAllFavoriteFood() : Flow<List<FoodEntity>> = foodDao.getAllFavoriteFood()

    fun getDetailFood(foodId : String): Flow<FoodEntity> = foodDao.getDetailFood(foodId)

    suspend fun updateFavorite(food: FoodEntity) = foodDao.updateFavorite(food)

}