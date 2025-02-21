package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MealEntity
import com.example.core.data.source.local.room.MealDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealDao : MealDao) {

    fun getAllMeal() : Flow<List<MealEntity>> = mealDao.getAllMeal()

    fun getAllFavoriteMeal() : Flow<List<MealEntity>> = mealDao.getAllFavoriteMeal()

    fun getDetailMeal(mealId : String) : Flow<MealEntity> = mealDao.getDetailMeal(mealId)

    fun updateFavorite(meal : MealEntity, state : Boolean){
        meal.isFavorite = state
        mealDao.updateFavorite(meal)
    }

    suspend fun insertMeal(meal : List<MealEntity>) = mealDao.insertMeal(meal)

    fun searchMeal(query : String) = mealDao.searchMeal(query)
}