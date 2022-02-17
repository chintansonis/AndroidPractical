package com.example.chintanandroidpractical.database.converters

import androidx.room.TypeConverter
import com.example.chintanandroidpractical.models.networkmodels.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class ImageConverter {
  @TypeConverter
  fun fromString(value: String): Images? {
    val listType = object : TypeToken<Images>() {}.type
    return Gson().fromJson<Images>(value, listType)
  }

  @TypeConverter
  fun fromList(list: Images?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
