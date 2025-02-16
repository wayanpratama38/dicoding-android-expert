package com.example.foodist.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodist.databinding.FoodListItemBinding
import com.example.foodist.domain.model.Food

class RvAdapter : ListAdapter<Food, RvAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick : ((Food)->Unit)? = null

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Food> =
            object : DiffUtil.ItemCallback<Food>() {
                override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                    return oldItem == newItem
                }
            }
    }

    inner class ListViewHolder(private var binding : FoodListItemBinding  ) :RecyclerView.ViewHolder(binding.root) {
        fun bind(data :Food){
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.title
            binding.tvItemSubtitle.text = data.difficulty
        }

        init{
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
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