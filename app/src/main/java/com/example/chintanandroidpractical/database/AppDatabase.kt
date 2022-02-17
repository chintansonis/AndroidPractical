package com.example.chintanandroidpractical.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chintanandroidpractical.database.converters.ImageConverter
import com.example.chintanandroidpractical.database.converters.IntegerListConverter
import com.example.chintanandroidpractical.database.converters.PriceConverter
import com.example.chintanandroidpractical.models.entities.Summary
import com.example.chintanandroidpractical.database.converters.StringListConverter

@Database(
    entities = [(Summary::class)], version = 2, exportSchema = false
)
@TypeConverters(
    value = [(StringListConverter::class),(IntegerListConverter::class),(ImageConverter::class),(PriceConverter::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun summaryDao(): SummaryDao
}
