package com.example.foodist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal (
    val idMeal : String? = null,
    val strMeal : String? = null,
    val strArea : String? = null,
    val strInstruction : String? = null,
    val strMealThumb : String? = null,
    val strTags : String? =null,
    val strIngredient10: String? = null,
    val strIngredient12: String? = null,
    val strIngredient11: String? = null,
    val strIngredient14: String? = null,
    val strCategory: String? = null,
    val strIngredient13: String? = null,
    val strIngredient15: String? = null,
    val strIngredient5: String? = null,
    val strIngredient4: String? = null,
    val strIngredient7: String? = null,
    val strIngredient6: String? = null,
    val strIngredient9: String? = null,
    val strIngredient8: String? = null,
    val strIngredient1: String? = null,
    val strIngredient3: String? = null,
    val strIngredient2: String? = null,
    var isFavorite : Boolean? =null,
) : Parcelable