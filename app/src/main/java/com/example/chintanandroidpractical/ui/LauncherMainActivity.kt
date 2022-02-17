package com.example.chintanandroidpractical.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import com.example.chintanandroidpractical.ui.screens.NavigateToHomeBottomBarScreen
import com.example.chintanandroidpractical.ui.screens.homeproductlist.LoadHomeProductViewModel
import com.example.chintanandroidpractical.ui.theme.ChintanAndroidPracticalTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint

// Single Activity per app
@AndroidEntryPoint
class LauncherMainActivity : ComponentActivity() {
    private val viewModel: LoadHomeProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
                ChintanAndroidPracticalTheme {
                    NavigateToHomeBottomBarScreen()
                }
            }
        }
    }

}