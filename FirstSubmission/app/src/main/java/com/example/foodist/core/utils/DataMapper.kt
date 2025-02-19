package com.example.foodist.core.utils

import com.example.foodist.core.data.source.local.entity.MealEntity
import com.example.foodist.core.data.source.remote.response.MealItem

import com.example.foodist.core.domain.model.Meal

object DataMapper {

    fun mapDomainToEntity(input : Meal) =
        MealEntity(
            idMeal = input.idMeal,
            strMeal = input.strMeal,
            strArea = input.strArea,
            strInstruction = input.strInstruction,
            strMealThumb = input.strMealThumb,
            strTags = input.strTags,
            strIngredient10 = input.strIngredient10,
            strIngredient12 = input.strIngredient12,
            strIngredient11 = input.strIngredient11,
            strIngredient14 = input.strIngredient14,
            strCategory = input.strCategory,
            strIngredient13 = input.strIngredient13,
            strIngredient15 = input.strIngredient15,
            strIngredient5 = input.strIngredient5,
            strIngredient4 = input.strIngredient4,
            strIngredient7 = input.strIngredient7,
            strIngredient6 = input.strIngredient6,
            strIngredient9 = input.strIngredient9,
            strIngredient8 = input.strIngredient8,
            strIngredient1 = input.strIngredient1,
            strIngredient3 = input.strIngredient3,
            strIngredient2 = input.strIngredient2,
            isFavorite = input.isFavorite
        )


    fun mapResponseToEntity(mealItem : MealItem) : MealEntity {
        return MealEntity(
            idMeal = mealItem.idMeal,
            strMeal = mealItem.strMeal,
            strMealThumb = mealItem.strMealThumb,
        )
    }



    fun mapEntitiesToDomain(input : List<MealEntity>) : List<Meal>{
        return input.map { entities ->
            Meal(
                idMeal = entities.idMeal,
                strMeal = entities.strMeal,
                strArea = "",
                strInstruction = "",
                strMealThumb = entities.strMealThumb,
                strTags = "",
                strIngredient10 = "",
                strIngredient12 = "",
                strIngredient11 = "",
                strIngredient14 = "",
                strCategory = "",
                strIngredient13 = "",
                strIngredient15 = "",
                strIngredient5 = "",
                strIngredient4 = "",
                strIngredient7 = "",
                strIngredient6 = "",
                strIngredient9 = "",
                strIngredient8 = "",
                strIngredient1 = "",
                strIngredient3 = "",
                strIngredient2 = "",
                isFavorite = entities.isFavorite
            )
        }
    }


    fun mapDetailResponseToEntity(input : MealItem) : MealEntity {
        return MealEntity(
            idMeal = input.idMeal,
            strMeal = input.strMeal,
            strArea = input.strArea,
            strInstruction = input.strInstructions,
            strMealThumb = input.strMealThumb,
            strTags = input.strTags,
            strIngredient10 = input.strIngredient10,
            strIngredient12 = input.strIngredient12,
            strIngredient11 = input.strIngredient11,
            strIngredient14 = input.strIngredient14,
            strCategory = input.strCategory,
            strIngredient13 = input.strIngredient13,
            strIngredient15 = input.strIngredient15,
            strIngredient5 = input.strIngredient5,
            strIngredient4 = input.strIngredient4,
            strIngredient7 = input.strIngredient7,
            strIngredient6 = input.strIngredient6,
            strIngredient9 = input.strIngredient9,
            strIngredient8 = input.strIngredient8,
            strIngredient1 = input.strIngredient1,
            strIngredient3 = input.strIngredient3,
            strIngredient2 = input.strIngredient2,
        )
    }


    fun mapDetailEntitiesToDomain(input: MealEntity) : Meal {
        return Meal(
            idMeal = input.idMeal,
            strMeal = input.strMeal,
            strArea = input.strArea,
            strInstruction = input.strInstruction,
            strMealThumb = input.strMealThumb,
            strTags = input.strTags,
            strIngredient10 = input.strIngredient10,
            strIngredient12 = input.strIngredient12,
            strIngredient11 = input.strIngredient11,
            strIngredient14 = input.strIngredient14,
            strCategory = input.strCategory,
            strIngredient13 = input.strIngredient13,
            strIngredient15 = input.strIngredient15,
            strIngredient5 = input.strIngredient5,
            strIngredient4 = input.strIngredient4,
            strIngredient7 = input.strIngredient7,
            strIngredient6 = input.strIngredient6,
            strIngredient9 = input.strIngredient9,
            strIngredient8 = input.strIngredient8,
            strIngredient1 = input.strIngredient1,
            strIngredient3 = input.strIngredient3,
            strIngredient2 = input.strIngredient2,
            isFavorite = input.isFavorite
        )
    }
}