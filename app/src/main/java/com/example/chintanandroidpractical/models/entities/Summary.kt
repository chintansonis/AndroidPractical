package com.example.chintanandroidpractical.models.entities

import androidx.compose.runtime.Immutable
import androidx.room.Embedded
import androidx.room.Entity
import com.example.chintanandroidpractical.models.networkmodels.Images
import com.example.chintanandroidpractical.models.networkmodels.Price

@Immutable
@Entity(primaryKeys = [("id")])
data class Summary(
    val analyticsKey: String,
    val badges: List<String>,
    val brandId: Int,
    val colourIds: List<Int>,
    val id: Int,
    val isFavouriteAdded: Boolean,
    val images: Images,
    val leafCategoryIds: List<Int>,
    val name: String,
    val onSale: Boolean,
    val price: Price,
    val saleableStandardSizeIds: List<String>,
    val visible: Boolean
)