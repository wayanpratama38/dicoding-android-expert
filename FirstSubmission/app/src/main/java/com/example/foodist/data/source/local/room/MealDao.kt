package com.example.foodist.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodist.data.source.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    // Get All Meals
    @Query("SELECT * FROM meal")
    fun getAllMeal() : Flow<List<MealEntity>>

    // Get Detail Meal Information
    @Query("SELECT * FROM meal WHERE meal.idMeal= :idMeal LIMIT 1")
    fun getDetailMeal(idMeal : String) : Flow<MealEntity>

    // Untuk Insert Meal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal : List<MealEntity>)

    // Update Favorite Meal
    @Update
    fun updateFavorite(meal : MealEntity)

    // Get All Favorite Meal
    @Query("SELECT * FROM meal WHERE isFavorite=1")
    fun getAllFavoriteMeal() : Flow<List<MealEntity>>
}