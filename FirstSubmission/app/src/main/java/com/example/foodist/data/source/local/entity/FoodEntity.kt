package com.example.foodist.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "food")
data class FoodEntity (
    @PrimaryKey
    val id : String,

    @ColumnInfo("name")
    val title : String,

    @ColumnInfo("image")
    val image : String,

    @ColumnInfo("difficulty")
    val difficulty : String,

    @ColumnInfo("time")
    val time : String,

    @ColumnInfo("description")
    val description : String,

    @ColumnInfo("portion")
    val portion : String,

    var isFavorite : Boolean = false // Default as False
) : Parcelable
