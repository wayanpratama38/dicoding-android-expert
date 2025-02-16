package com.example.foodist.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodist.R
import com.example.foodist.data.source.Resource
import com.example.foodist.databinding.FragmentHomeBinding
import com.example.foodist.domain.model.Food
import com.example.foodist.presentation.detail.Detail
import com.example.foodist.presentation.utils.RvAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private var _binding  : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){
            val foodAdapter = RvAdapter()
            foodAdapter.onItemClick = { itemSelected->
                val intent = Intent(activity,Detail::class.java)
                intent.putExtra(Detail.EXTRA_DATA,itemSelected)
                startActivity(intent)
            }

            homeViewModel.food.observe(viewLifecycleOwner) { food ->
                Log.d("HomeFragment","Data diterima : ${food}")
                if (food != null) {
                    when (food) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            Log.d("HomeFragment","${food.message}")
                            binding.progressBar.visibility = View.GONE
                            foodAdapter.submitList(food.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE

                        }
                    }
                }
            }

            with(binding.recyclerView){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = foodAdapter
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


}