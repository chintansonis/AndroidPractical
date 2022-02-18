package com.example.chintanandroidpractical.models.entities

import androidx.compose.runtime.Immutable
import androidx.room.Entity


// FavouriteSummary table is used for storing favourite items
@Immutable
@Entity(primaryKeys = [("id")])
data class FavouriteSummary(
    val id: Int,
    var summary: Summary
)