package com.example.chintanandroidpractical.models.networkmodels

import androidx.compose.runtime.Immutable

@Immutable
data class Images(
    val mediaType: String,
    val shots: List<String>,
    val sizes: List<String>,
    val urlTemplate: String
)