package com.example.foodist.presentation.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodist.databinding.FoodListItemBinding
import com.example.foodist.domain.model.Meal

class RvAdapter : ListAdapter<Meal, RvAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick : ((Meal)->Unit)? = null

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Meal> =
            object : DiffUtil.ItemCallback<Meal>() {
                override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                    return oldItem.idMeal == newItem.idMeal
                }

                override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                    return oldItem == newItem
                }
            }
    }

    inner class ListViewHolder(private var binding : FoodListItemBinding  ) :RecyclerView.ViewHolder(binding.root) {
        fun bind(data :Meal){
            Log.d("Adapter","${data}")
            Log.d("Adapter","Image URL :  ${data.idMeal} ${data.strMeal} ${data.strMealThumb}")
            Glide.with(itemView.context)
                .load(data.strMealThumb)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.strMeal
            binding.tvItemSubtitle.text = data.strArea
        }

        init{
            itemView.setOnClickListener {
//                onItemClick?.invoke(getItem(bindingAdapterPosition))
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(getItem(position))
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ListViewHolder {
        return ListViewHolder(
                FoodListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }


    override fun onBindViewHolder(holder: RvAdapter.ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}