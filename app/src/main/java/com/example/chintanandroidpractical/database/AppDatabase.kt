package com.example.chintanandroidpractical.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chintanandroidpractical.database.converters.*
import com.example.chintanandroidpractical.models.entities.Summary
import com.example.chintanandroidpractical.models.entities.FavouriteSummary

@Database(
    entities = [(Summary::class),(FavouriteSummary::class)], version = 2, exportSchema = false
)
@TypeConverters(
    value = [(StringListConverter::class),(IntegerListConverter::class),(ImageConverter::class),(PriceConverter::class),(SummaryConverter::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun summaryDao(): SummaryDao
}
