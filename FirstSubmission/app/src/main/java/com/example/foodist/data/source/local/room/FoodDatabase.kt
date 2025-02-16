package com.example.foodist.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodist.data.source.local.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao() : FoodDao

}