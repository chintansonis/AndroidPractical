package com.example.chintanandroidpractical.models.entities

import androidx.compose.runtime.Immutable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chintanandroidpractical.models.networkmodels.Images
import com.example.chintanandroidpractical.models.networkmodels.Price

@Immutable
@Entity(tableName = "favorite_table")
data class FavouriteSummary(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var favouriteSummary: Summary
)