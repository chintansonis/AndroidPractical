package com.example.chintanandroidpractical.models.entities

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import com.example.chintanandroidpractical.models.networkmodels.Images
import com.example.chintanandroidpractical.models.networkmodels.Price

// Summary table is used for storing main exploring products
@Immutable
@Entity(primaryKeys = [("id")])
data class Summary(
    val analyticsKey: String,
    val badges: List<String>,
    val brandId: Int,
    val colourIds: List<Int>,
    val id: Int,
    val images: Images,
    // this variable is keep track whether user selected fav item or not
    var isFavouriteAdded: Boolean,
    val leafCategoryIds: List<Int>,
    val name: String,
    val onSale: Boolean,
    val price: Price,
    val saleableStandardSizeIds: List<String>,
    val visible: Boolean
)