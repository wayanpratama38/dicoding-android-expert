package com.example.foodist.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.foodist.R
import com.example.foodist.data.source.Resource
import com.example.foodist.databinding.ActivityDetailBinding
import com.example.foodist.domain.model.Food
import com.example.foodist.domain.model.Meal
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class Detail : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private val foodId : String by lazy { intent.getStringExtra(FOOD_ID) ?: "" }
    private val detailViewModel : DetailViewModel by viewModel{ parametersOf(foodId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observeViewModel()
    }

    private fun observeViewModel(){
        detailViewModel.meal.observe(this){ detailMeal->
            when(detailMeal){
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showDetailMeal(detailMeal.data)
                }
                is Resource.Error ->  binding.progressBar.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMeal(detailMeal : Meal?){
            detailMeal?.let {
                Log.d("DetailScreen","${detailMeal.strMealThumb} ${detailMeal.strMeal}")
                binding.detailInformation.tvDescription.text = it.strInstruction
                Glide.with(this@Detail)
                    .load(detailMeal.strMealThumb)
                    .into(binding.ivFoodImage)
                binding.detailInformation.tvTitleFood.text = it.strMeal
                binding.detailInformation.tvPortion.text = it.strTags?:"No Tag"
                binding.detailInformation.tvTime.text = it.strCategory
                binding.detailInformation.tvDifficulty.text = it.strArea
                var statusFavorite = it.isFavorite
                setStatusFavorite(statusFavorite)
                binding.fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite!!
                    detailViewModel.setFavoriteFood(detailMeal, statusFavorite!!)
                    setStatusFavorite(statusFavorite)
                }

            }
    }

    private fun setStatusFavorite(boolean: Boolean?){
        if(boolean!!){
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_24))
        }else{
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_border_24))
        }
    }
    companion object{
        const val EXTRA_DATA = "extra_data"
        const val FOOD_ID = "food_id"
    }
}