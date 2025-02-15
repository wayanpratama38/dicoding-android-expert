package com.example.foodist.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodist.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {

    // Get All Food
    @Query("SELECT * FROM food")
    fun getAllFood() : Flow<List<FoodEntity>>

    // Get Detail Food Information
    @Query("SELECT * FROM food WHERE food.id= :foodId LIMIT 1")
    fun getDetailFood(foodId : String) : Flow<FoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // Untuk Insert Food
    suspend fun insertFood(food : List<FoodEntity>) :Flow<List<FoodEntity>>

    @Update
    // Update Favorite Food
    fun updateFavorite(food : FoodEntity,state :Boolean)

    // Get All Favorite Food
    @Query("SELECT * FROM food WHERE isFavorite=1")
    fun getAllFavoriteFood() : Flow<List<FoodEntity>>
}
