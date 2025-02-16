package com.example.foodist.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodDetailResponse(

	@field:SerializedName("difficulty")
	val difficulty: String,

	@field:SerializedName("image")
	val image: String,


	@field:SerializedName("portion")
	val portion: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("title")
	val title: String
)

data class MethodItem(

	@field:SerializedName("Step 4")
	val step4: String? = null,

	@field:SerializedName("Step 3")
	val step3: String? = null,

	@field:SerializedName("Step 2")
	val step2: String? = null,

	@field:SerializedName("Step 1")
	val step1: String? = null
)
