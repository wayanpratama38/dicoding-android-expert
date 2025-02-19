package com.example.foodist.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "meal")
data class MealEntity(
    @PrimaryKey
    val idMeal : String? = null,

    @ColumnInfo
    val strMeal : String? = null,

    @ColumnInfo
    val strArea : String? = null,

    @ColumnInfo
    val strInstruction : String? = null,

    @ColumnInfo
    val strMealThumb : String? = null,

    @ColumnInfo
    val strTags : String? =null,

    @ColumnInfo
    val strIngredient10: String? = null,

    @ColumnInfo
    val strIngredient12: String? = null,

    @ColumnInfo
    val strIngredient11: String? = null,

    @ColumnInfo
    val strIngredient14: String? = null,

    @ColumnInfo
    val strCategory: String? = null,

    @ColumnInfo
    val strIngredient13: String? = null,

    @ColumnInfo
    val strIngredient15: String? = null,

    @ColumnInfo
    val strIngredient5: String? = null,

    @ColumnInfo
    val strIngredient4: String? = null,

    @ColumnInfo
    val strIngredient7: String? = null,

    @ColumnInfo
    val strIngredient6: String? = null,

    @ColumnInfo
    val strIngredient9: String? = null,

    @ColumnInfo
    val strIngredient8: String? = null,

    @ColumnInfo
    val strIngredient1: String? = null,

    @ColumnInfo
    val strIngredient3: String? = null,

    @ColumnInfo
    val strIngredient2: String? = null,

    // for favorite
    var isFavorite : Boolean? =null,

    ):Parcelable