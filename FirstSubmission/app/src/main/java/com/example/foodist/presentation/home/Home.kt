package com.example.foodist.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.source.Resource
import com.example.foodist.databinding.FragmentHomeBinding
import com.example.foodist.presentation.detail.Detail
import com.example.foodist.presentation.utils.RvAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalCoroutinesApi::class)
class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private var _binding  : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var scrollPosition : Int= 0

    @SuppressLint("ClickableViewAccessibility")
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

            // observe first
            observeMeal(mealAdapter)

            // handle search
            observeSearch(mealAdapter)

            with(binding.recyclerView){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mealAdapter
                
                post{
                    (layoutManager as LinearLayoutManager).scrollToPosition(scrollPosition)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun observeSearch(mealAdapter : RvAdapter){
        binding.searchBar.includeSearchView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                binding.searchBar.includeSearchView.isIconified = false
                binding.searchBar.includeSearchView.requestFocus()

                val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(binding.searchBar.includeSearchView, InputMethodManager.SHOW_IMPLICIT)
                binding.searchBar.includeSearchView.performClick()
            }
            true
        }


        binding.searchBar.includeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query.isNullOrEmpty()){
                    observeMeal(mealAdapter)
                }else{
                    query.let {
                        homeViewModel.searchMeal(it)
                        if(it.isNotEmpty()){
                            searchHandle(mealAdapter)
                        }else{
                            binding.tvUnavailable.visibility = View.VISIBLE
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()){
                    observeMeal(mealAdapter)
                }else{
                    newText.let {
                        homeViewModel.searchMeal(it)
                        if(it.isNotEmpty()){
                            searchHandle(mealAdapter)
                        }else{
                            binding.tvUnavailable.visibility = View.VISIBLE
                        }
                    }
                }
                return true
            }
        })


    }

    private fun searchHandle(mealAdapter: RvAdapter){
        homeViewModel.searchMeal.observe(viewLifecycleOwner){meal->
            mealAdapter.submitList(meal)
            if(meal.isNotEmpty()){
                binding.tvUnavailable.visibility = View.GONE
            }else{
                binding.tvUnavailable.visibility = View.VISIBLE
            }
        }
    }


    private fun observeMeal(mealAdapter : RvAdapter){
        homeViewModel.meal.observe(viewLifecycleOwner) { meal ->
            if (meal != null) {
                when (meal) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
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
        outState.putInt("SCROLL_POSITION",scrollPosition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        scrollPosition = savedInstanceState?.getInt("SCROLL_POSITION", scrollPosition) ?: 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}