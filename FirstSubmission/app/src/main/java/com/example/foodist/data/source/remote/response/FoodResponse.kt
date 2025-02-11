package com.example.foodist.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodResponse(

	@field:SerializedName("FoodResponse")
	val foodResponse: List<FoodResponseItem?>? = null
)

data class FoodResponseItem(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
