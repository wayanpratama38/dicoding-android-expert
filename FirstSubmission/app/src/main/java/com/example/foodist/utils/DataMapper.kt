package com.example.foodist.utils

import com.example.foodist.data.source.local.entity.FoodEntity
import com.example.foodist.data.source.remote.response.FoodDetailResponse
import com.example.foodist.data.source.remote.response.FoodResponse
import com.example.foodist.data.source.remote.response.FoodResponseItem
import com.example.foodist.domain.model.Food

object DataMapper {

    fun mapDomainToEntity(input: Food) =
        FoodEntity(
        id = input.id,
        title = input.title,
        image = input.image,
        difficulty = input.difficulty,
        time = input.time,
        description = input.description,
        portion = input.portion,
        isFavorite = input.isFavorite
    )

    fun mapResponseToEntity(input : List<FoodResponseItem>) : List<FoodEntity>{
        return input.map { response ->
            FoodEntity(
                id = response.id,
                title = response.title,
                image = response.image,
                difficulty = response.difficulty,
                time = "",
                portion = "",
                description = ""
            )
        }
    }

    fun mapEntitiesToDomain(input : List<FoodEntity>) : List<Food>{
        return input.map { response ->
            Food(
                id = response.id,
                title = response.title,
                image = response.title,
                time = response.time,
                description = response.description,
                difficulty = response.difficulty,
                isFavorite = response.isFavorite,
                portion = response.portion,
            )
        }
    }

    fun mapDetailResponseToEntity(input : FoodDetailResponse) : FoodEntity {
        return FoodEntity(
            id = input.id,
            title = input.title,
            image = input.image,
            difficulty = input.difficulty,
            time = input.time,
            portion = input.portion,
            description = input.description
        )
    }

    fun mapDetailEntitiesToDomain(input : FoodEntity) : Food{
        return Food(
                id = input.id,
                title = input.title,
                image = input.title,
                time = input.time,
                description = input.description,
                difficulty = input.difficulty,
                isFavorite = input.isFavorite,
                portion = input.portion,
            )

    }
}