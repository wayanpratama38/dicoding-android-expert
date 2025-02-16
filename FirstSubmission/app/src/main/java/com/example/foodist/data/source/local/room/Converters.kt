package com.example.foodist.data.source.local.room

import androidx.room.TypeConverter
import com.example.foodist.data.source.remote.response.MethodItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromMethodItemList(value: List<MethodItem>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMethodItemList(value: String): List<MethodItem> {
        return Gson().fromJson(value, object : TypeToken<List<MethodItem>>() {}.type)
    }
}