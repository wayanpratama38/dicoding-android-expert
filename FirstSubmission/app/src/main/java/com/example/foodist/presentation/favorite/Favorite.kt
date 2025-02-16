package com.example.foodist.presentation.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Visibility
import com.example.foodist.R
import com.example.foodist.data.source.Resource
import com.example.foodist.databinding.FragmentFavoriteBinding
import com.example.foodist.presentation.detail.Detail
import com.example.foodist.presentation.utils.RvAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class Favorite : Fragment() {

    private val favoriteViewModel : FavoriteViewModel by viewModel()
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){

            val foodAdapter = RvAdapter()
            foodAdapter.onItemClick = { itemSelected ->
                val intent = Intent(activity,Detail::class.java)
                intent.putExtra(Detail.EXTRA_DATA,itemSelected)
                startActivity(intent)
            }

            favoriteViewModel.food.observe(viewLifecycleOwner) {food ->
                foodAdapter.submitList(food)
                binding.tvFavoriteEmpty.visibility =
                    if(food.isNotEmpty()) View.GONE else View.VISIBLE
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

}