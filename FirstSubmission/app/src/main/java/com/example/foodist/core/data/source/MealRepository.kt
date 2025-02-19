package com.example.foodist.core.data.source

import android.util.Log
import com.example.foodist.core.data.source.local.LocalDataSource
import com.example.foodist.core.data.source.remote.RemoteDataSource
import com.example.foodist.core.data.source.remote.network.ApiResponse
import com.example.foodist.core.data.source.remote.response.MealItem
import com.example.foodist.core.domain.model.Meal
import com.example.foodist.core.domain.repository.IMealRepository
import com.example.foodist.core.utils.AppExecutors
import com.example.foodist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class MealRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors,
) : IMealRepository {
    override fun getAllMeals(): Flow<Resource<List<Meal>>> =
        object : NetworkBoundResource<List<Meal>, List<MealItem>>(){
            override fun loadFromDB(): Flow<List<Meal>> {
                Log.d("MealRepository", "getAllMeals() called")
                return localDataSource.getAllMeal().map{mealEntities ->
                    DataMapper.mapEntitiesToDomain(mealEntities)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MealItem>>>  = remoteDataSource.getAllMeals()

            override suspend fun saveCallResult(data: List<MealItem>) {
                val mealList = data.map {mealItem ->
                    when(val detailResponse = remoteDataSource.getDetailMeal(mealItem.idMeal).firstOrNull()){
                        is ApiResponse.Success -> DataMapper.mapDetailResponseToEntity(detailResponse.data)
                        else -> DataMapper.mapResponseToEntity(mealItem)
                    }
                }

                localDataSource.insertMeal(mealList)
            }

            override fun shouldFetch(data: List<Meal>?): Boolean {
                Log.d("MealRepository", "shouldFetch() called, data is ${data?.size}")
                return data.isNullOrEmpty()
            }
        }.asFlow()


    override fun getDetailMeal(id: String): Flow<Resource<Meal>> =
        object : NetworkBoundResource<Meal, MealItem>(){
            override fun loadFromDB(): Flow<Meal> {
                return localDataSource.getDetailMeal(id).map{
                    DataMapper.mapDetailEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<MealItem>> {
                return remoteDataSource.getDetailMeal(id)
            }

            override suspend fun saveCallResult(data: MealItem) {
                val foodEntity = DataMapper.mapDetailResponseToEntity(data)
                val existingFood = localDataSource.getDetailMeal(id).firstOrNull()
                if(existingFood == null){
                    Log.d("FoodRepository", "Saving new data to database")
                    localDataSource.insertMeal(listOf(foodEntity))
                }else{
                    Log.d("FoodRepository", "Data Already Exist,skipp")
                }
            }

            override fun shouldFetch(data: Meal?): Boolean {
                return data == null
            }
        }.asFlow()


    override fun getAllFavoriteMeal(): Flow<List<Meal>> {
        return localDataSource.getAllFavoriteMeal().map{
            DataMapper.mapEntitiesToDomain(it)
        }
    }


    override fun setFavoriteMeal(meal: Meal, state: Boolean) {
        val mealEntity = DataMapper.mapDomainToEntity(meal)
        appExecutors.diskIO().execute{ localDataSource.updateFavorite(mealEntity,state)}
    }
}