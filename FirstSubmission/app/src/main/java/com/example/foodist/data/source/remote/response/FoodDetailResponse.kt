package com.example.foodist.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodDetailResponse(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("method")
	val method: List<MethodItem?>? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("ingredients")
	val ingredients: List<String?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("title")
	val title: String? = null
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
