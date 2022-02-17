package com.example.chintanandroidpractical.utils

import androidx.annotation.StringRes
import com.example.chintanandroidpractical.R


sealed class NavigationItem(var route: String, var icon: Int, @StringRes val title: Int) {
    object BottomHomeProductList : NavigationItem("explore_products", R.drawable.ic_bottombar_clothing,  R.string.explore)
    object BottomFavouriteProductList : NavigationItem("favourite_products", R.drawable.ic_bottombar_favourite, R.string.favourite)
}