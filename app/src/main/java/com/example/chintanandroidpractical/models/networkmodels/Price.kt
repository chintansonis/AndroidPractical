package com.example.chintanandroidpractical.models.networkmodels

import androidx.compose.runtime.Immutable


@Immutable
data class Price(
    val amount: Int,
    val currency: String,
    val divisor: Int
)