package com.example.chintanandroidpractical.ui.screens.favouriteproductlist

import androidx.compose.runtime.Composable
import com.example.chintanandroidpractical.R
import com.example.chintanandroidpractical.ui.LoadEmptyView

@Composable
fun LoadFavouritesProductsScreen() {
    LoadEmptyView(R.string.no_favourites_found)
}