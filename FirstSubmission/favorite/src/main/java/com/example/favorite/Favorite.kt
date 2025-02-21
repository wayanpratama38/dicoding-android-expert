package com.example.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.foodist.presentation.detail.Detail
import com.example.foodist.presentation.utils.RvAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class Favorite : Fragment() {

    private val favoriteViewModel : FavoriteViewModel by viewModel()
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){
            val mealAdapter = RvAdapter()
            mealAdapter.onItemClick = { itemSelected ->
                val intent = Intent(requireContext(), Detail::class.java)
                intent.putExtra(Detail.FOOD_ID,itemSelected.idMeal)
                startActivity(intent)
            }

            favoriteViewModel.meal.observe(viewLifecycleOwner) {meal ->
                mealAdapter.submitList(meal)
                binding.tvFavoriteEmpty.visibility =
                    if(meal.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.recyclerView) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mealAdapter
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