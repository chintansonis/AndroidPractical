package com.example.chintanandroidpractical.utils

import androidx.annotation.StringRes
import com.example.chintanandroidpractical.R

/**
 * sealed class enum for navigations
 */
sealed class NavigationItem(var route: String, var icon: Int, @StringRes val title: Int) {

    // navigation for Spalsh
    object Splash : NavigationItem("splash_screen", 0,0)
    // navigation for Home container to manage 2 bottom tabs
    object HomeScreenNavigation : NavigationItem("home_screen", 0,0)
    // navigation for explore products bottom tab
    object BottomHomeProductList : NavigationItem("explore_products", R.drawable.ic_bottombar_clothing,  R.string.explore)
    // navigation for favourite products bottom tab
    object BottomFavouriteProductList : NavigationItem("favourite_products", R.drawable.ic_bottombar_favourite, R.string.favourite)
}