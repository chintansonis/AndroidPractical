package com.example.chintanandroidpractical.models.networkmodels

import androidx.compose.runtime.Immutable
import com.example.chintanandroidpractical.models.NetworkResponseModel
import com.example.chintanandroidpractical.models.entities.Summary

@Immutable
data class ResponseProducts(
    val listInfo: ListInfo,
    val summaries: List<Summary>
): NetworkResponseModel