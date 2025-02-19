package com.example.foodist.di

import androidx.room.Room
import com.example.foodist.data.source.MealRepository
import com.example.foodist.data.source.local.LocalDataSource
import com.example.foodist.data.source.local.room.MealDatabase
import com.example.foodist.data.source.remote.RemoteDataSource
import com.example.foodist.data.source.remote.network.ApiService
import com.example.foodist.domain.repository.IMealRepository
import com.example.foodist.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module{
//    factory { get<FoodDatabase>().foodDao() }
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            FoodDatabase::class.java, "Food.db"
//        ).fallbackToDestructiveMigration().build()
//    }
    factory { get<MealDatabase>().mealDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MealDatabase::class.java,"Meal.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMealRepository> { MealRepository(get(),get(),get()) }
}
