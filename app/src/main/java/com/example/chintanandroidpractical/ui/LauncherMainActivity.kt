package com.example.chintanandroidpractical.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chintanandroidpractical.ui.screens.NavigateToHomeBottomBarScreen
import com.example.chintanandroidpractical.ui.screens.SplashScreen
import com.example.chintanandroidpractical.ui.theme.ChintanAndroidPracticalTheme
import com.example.chintanandroidpractical.utils.NavigationItem
import dagger.hilt.android.AndroidEntryPoint

// Single Activity per app
@AndroidEntryPoint
class LauncherMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChintanAndroidPracticalTheme {
                Navigation()
            }
        }
    }

    // setup initial navigation to navigate Main Homescreen
    @Composable
    private fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = NavigationItem.Splash.route
        ) {
            composable(NavigationItem.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(NavigationItem.HomeScreenNavigation.route) {
                NavigateToHomeBottomBarScreen()
            }
        }
    }

}