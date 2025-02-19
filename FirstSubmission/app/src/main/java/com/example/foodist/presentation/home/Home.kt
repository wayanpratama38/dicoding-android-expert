package com.example.foodist.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodist.core.data.source.Resource
import com.example.foodist.databinding.FragmentHomeBinding
import com.example.foodist.presentation.detail.Detail
import com.example.foodist.presentation.utils.RvAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private var _binding  : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var scrollPosition : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){
            val mealAdapter = RvAdapter()
            mealAdapter.onItemClick = { itemSelected->
                scrollPosition = (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val intent = Intent(requireContext(),Detail::class.java)
                intent.putExtra(Detail.FOOD_ID,itemSelected.idMeal)
                startActivity(intent)
            }

            homeViewModel.meal.observe(viewLifecycleOwner) { meal ->
                Log.d("HomeFragment","Data : $meal")
                if (meal != null) {
                    when (meal) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            Log.d("HomeFragment","${meal.message}")
                            binding.progressBar.visibility = View.GONE
                            mealAdapter.submitList(meal.data)
                            binding.recyclerView.layoutManager?.scrollToPosition(0)
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
                adapter = mealAdapter
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("HomeFragment", "Simpan posisi scroll: $scrollPosition")
        outState.putInt("SCROLL_POSITION",scrollPosition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        scrollPosition = savedInstanceState?.getInt("SCROLL_POSITION", scrollPosition) ?: 0
        Log.d("HomeFragment", "Scroll ke : $scrollPosition")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}