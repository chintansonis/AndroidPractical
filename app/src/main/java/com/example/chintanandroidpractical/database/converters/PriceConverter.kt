package com.example.chintanandroidpractical.database.converters

import androidx.room.TypeConverter
import com.example.chintanandroidpractical.models.networkmodels.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class PriceConverter {
  @TypeConverter
  fun fromString(value: String): Price? {
    val listType = object : TypeToken<Price>() {}.type
    return Gson().fromJson<Price>(value, listType)
  }

  @TypeConverter
  fun fromList(list: Price?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
