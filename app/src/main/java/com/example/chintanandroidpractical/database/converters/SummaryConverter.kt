package com.example.chintanandroidpractical.database.converters

import androidx.room.TypeConverter
import com.example.chintanandroidpractical.models.entities.Summary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class SummaryConverter {
  @TypeConverter
  fun fromString(value: String): Summary? {
    val listType = object : TypeToken<Summary>() {}.type
    return Gson().fromJson<Summary>(value, listType)
  }

  @TypeConverter
  fun fromList(list: Summary?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
