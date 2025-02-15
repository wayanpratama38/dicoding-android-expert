package com.example.foodist.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity (
    @PrimaryKey
    val id : String,

    @ColumnInfo("name")
    val title : String? = null,

    @ColumnInfo("smallImage")
    val smallImage : String? = null,

    @ColumnInfo("bigImage")
    val bigImage : String? = null,

    val isFavorite : Boolean = false // Default as False
)