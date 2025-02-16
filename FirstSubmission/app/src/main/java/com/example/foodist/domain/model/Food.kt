package com.example.foodist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Food(
    val id : String,
    val title : String,
    val image : String,
    val time : String,
    val description : String,
    val difficulty : String,
    val isFavorite : Boolean,
    val portion : String,
) : Parcelable
