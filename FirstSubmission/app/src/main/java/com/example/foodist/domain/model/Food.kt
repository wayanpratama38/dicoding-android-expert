package com.example.foodist.domain.model

import android.os.Parcelable
import com.example.foodist.data.source.local.entity.Method
import kotlinx.parcelize.Parcelize


@Parcelize
data class Food(
    val id : String,
    val title : String,
    val image : String,
    val time : String,
    val description : String,
    val difficulty : String,
    val ingredients: List<String?>?,
    val isFavorite : Boolean,
    val portion : String,
    val method : List<Method>
) : Parcelable
