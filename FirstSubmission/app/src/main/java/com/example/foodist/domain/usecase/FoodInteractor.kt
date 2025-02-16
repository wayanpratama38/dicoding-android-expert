package com.example.foodist.domain.usecase

import com.example.foodist.data.source.Resource
import com.example.foodist.domain.model.Food
import com.example.foodist.domain.repository.IFoodRepository
import kotlinx.coroutines.flow.Flow

class FoodInteractor(private val foodRepository : IFoodRepository) : FoodUseCase {
    override fun getAllFood(): Flow<Resource<List<Food>>>  = foodRepository.getAllFood()

    override fun getAllFavoriteFood() = foodRepository.getAllFavoriteFood()

    override fun getDetailFood(id: String): Flow<Resource<Food>> = foodRepository.getDetailFood(id)

    override fun setFavoriteFood(food: Food) = foodRepository.setFavoriteFood(food)

}