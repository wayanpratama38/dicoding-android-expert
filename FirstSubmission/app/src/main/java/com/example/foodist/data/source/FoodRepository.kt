package com.example.foodist.data.source

import android.net.Network
import android.util.Log
import com.example.foodist.data.source.local.LocalDataSource
import com.example.foodist.data.source.local.entity.FoodEntity
import com.example.foodist.data.source.remote.RemoteDataSource
import com.example.foodist.data.source.remote.network.ApiResponse
import com.example.foodist.data.source.remote.response.FoodDetailResponse
import com.example.foodist.data.source.remote.response.FoodResponse
import com.example.foodist.data.source.remote.response.FoodResponseItem
import com.example.foodist.domain.model.Food
import com.example.foodist.domain.repository.IFoodRepository
import com.example.foodist.utils.AppExecutors
import com.example.foodist.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

//class FoodRepository(
//    private val localDataSource: LocalDataSource,
//    private val remoteDataSource: RemoteDataSource,
//    private val appExecutors :AppExecutors
//) : IFoodRepository{
//
//
//    override fun getAllFood() : Flow<Resource<List<Food>>> =
//        object : NetworkBoundResource<List<Food>, List<FoodResponseItem>>() {
//            override fun loadFromDB(): Flow<List<Food>> {
////                return localDataSource.getAllFood().map{
////                    DataMapper.mapEntitiesToDomain(it)
////                }
//                return localDataSource.getAllFood().map { foodEntities->
//                    foodEntities.map { foodEntity->
//                        DataMapper.mapDetailEntitiesToDomain(foodEntity)
//                    }
//                }
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<List<FoodResponseItem>>> = remoteDataSource.getAllFood()
//
//            override suspend fun saveCallResult(data: List<FoodResponseItem>) {
////                val foodList = DataMapper.mapResponseToEntity(data)
////                localDataSource.insertFood(foodList)
//                val foodList = data.map { foodItem ->
//                    when (val detailResponse = remoteDataSource.getDetailFood(foodItem.id).firstOrNull()) {
//                        is ApiResponse.Success -> DataMapper.mapDetailResponseToEntity(detailResponse.data)
//                        else -> DataMapper.mapResponseToEntity(foodItem) // Jika gagal, tetap simpan data dasar
//                    }
//                }
//
//                localDataSource.insertFood(foodList)
//            }
//
//            override fun shouldFetch(data: List<Food>?): Boolean{
//                return data.isNullOrEmpty() || data.any {it.description.isEmpty() }
//            }
//
//        }.asFlow()
//
//    override fun getAllFavoriteFood(): Flow<List<Food>> {
//        return localDataSource.getAllFavoriteFood().map {
//            DataMapper.mapEntitiesToDomain(it)
//        }
//    }
//
//    override fun getDetailFood(id: String): Flow<Resource<Food>> =
//        object : NetworkBoundResource<Food, FoodDetailResponse>(){
//            override fun loadFromDB(): Flow<Food> {
//                return localDataSource.getDetailFood(id).map {
//                    DataMapper.mapDetailEntitiesToDomain(it)
//                }
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<FoodDetailResponse>> {
//                return remoteDataSource.getDetailFood(id)
//            }
//
//            override suspend fun saveCallResult(data: FoodDetailResponse) {
//                val foodEntity = DataMapper.mapDetailResponseToEntity(data)
//                val existingFood = localDataSource.getDetailFood(id).firstOrNull()
//                if(existingFood == null || existingFood.description.isEmpty()){
//                    Log.d("FoodRepository", "Saving new data to database")
//                    localDataSource.insertFood(listOf(foodEntity))
//                }else{
//                    Log.d("FoodRepository", "Data Already Exist,skipp")
//                }
//            }
//
//            override fun shouldFetch(data: Food?): Boolean {
//                return data == null || data.description.isEmpty()
//            }
//        }.asFlow()
//
//
//    override fun setFavoriteFood(food: Food,state: Boolean) {
//        val foodEntity = DataMapper.mapDomainToEntity(food)
//        appExecutors.diskIO().execute{ localDataSource.updateFavorite(foodEntity,state)}
//    }
//
//
//}