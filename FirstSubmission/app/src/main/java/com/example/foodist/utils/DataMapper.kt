package com.example.foodist.utils

import com.example.foodist.data.source.local.entity.FoodEntity
import com.example.foodist.data.source.local.entity.Method
import com.example.foodist.data.source.remote.response.FoodDetailResponse
import com.example.foodist.data.source.remote.response.FoodResponse
import com.example.foodist.data.source.remote.response.FoodResponseItem

object DataMapper {
    fun mapResponseToEntity(input : List<FoodResponseItem>) : List<FoodEntity>{
        return input.map { response ->
            FoodEntity(
                id = response.id ?: "",
                title = response.title,
                image = response.image,
                difficulty = response.difficulty,
                time = null,
                ingredients = null,
                method = null,
                portion = null,
                description = null
            )
        }
    }

    fun mapDetailResponseToEntity(input : FoodDetailResponse) : FoodEntity {
        return FoodEntity(
            id = input.id ?: "",
            title = input.title,
            image = input.image,
            difficulty = input.difficulty,
            time = input.time,
            ingredients = input.ingredients,
            method = input.method?.map {
                Method(
                    step1 = it?.step1,
                    step2 = it?.step2,
                    step3 = it?.step3,
                    step4 = it?.step4,
                )
            },
            portion = input.portion,
            description = input.description
        )
    }
}