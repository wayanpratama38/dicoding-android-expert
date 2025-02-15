package com.example.foodist.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodist.data.source.remote.response.MethodItem

@Entity(tableName = "food")
data class FoodEntity (
    @PrimaryKey
    val id : String,

    @ColumnInfo("name")
    val title : String? = null,

    @ColumnInfo("image")
    val image : String? = null,

    @ColumnInfo("difficulty")
    val difficulty : String?,

    @ColumnInfo("time")
    val time : String?,

    @ColumnInfo("ingredients")
    val ingredients : List<String?>?,

    @ColumnInfo("description")
    val description : String?,

    @ColumnInfo("portion")
    val portion : String?,

    @ColumnInfo("method")
    val method : List<Method>?,

    val isFavorite : Boolean = false // Default as False
)

data class Method(
    val step1 : String?,
    val step2 : String?,
    val step3 : String?,
    val step4 : String?,
)