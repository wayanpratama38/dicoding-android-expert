package com.example.foodist.data.source.local

import com.example.foodist.data.source.local.entity.MealEntity
import com.example.foodist.data.source.local.room.MealDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealDao : MealDao) {

//    fun getAllFood() : Flow<List<FoodEntity>> = foodDao.getAllFood()
//
//    fun getAllFavoriteFood() : Flow<List<FoodEntity>> = foodDao.getAllFavoriteFood()
//
//    fun getDetailFood(foodId : String): Flow<FoodEntity> = foodDao.getDetailFood(foodId)
//
//    fun updateFavorite(food: FoodEntity,state : Boolean) {
//        food.isFavorite = state
//        foodDao.updateFavorite(food)
//    }
//
//    suspend fun insertFood(food : List<FoodEntity>) = foodDao.insertFood(food)


    fun getAllMeal() : Flow<List<MealEntity>> = mealDao.getAllMeal()

    fun getAllFavoriteMeal() : Flow<List<MealEntity>> = mealDao.getAllFavoriteMeal()

    fun getDetailMeal(mealId : String) : Flow<MealEntity> = mealDao.getDetailMeal(mealId)

    fun updateFavorite(meal : MealEntity, state : Boolean){
        meal.isFavorite = state
        mealDao.updateFavorite(meal)
    }

    suspend fun insertMeal(meal : List<MealEntity>) = mealDao.insertMeal(meal)
}