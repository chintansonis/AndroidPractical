package com.example.chintanandroidpractical.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chintanandroidpractical.R
import com.example.chintanandroidpractical.ui.HomeBottomBarStateHolder
import com.example.chintanandroidpractical.ui.screens.favouriteproductlist.LoadFavouritesProductsScreen
import com.example.chintanandroidpractical.ui.screens.homeproductlist.LoadHomeProductViewModel
import com.example.chintanandroidpractical.ui.screens.homeproductlist.LoadHomeProductsScreen
import com.example.chintanandroidpractical.utils.NavigationItem


@Composable
fun NavigateToHomeBottomBarScreen() {
    val navController = rememberNavController()

    Scaffold(topBar = { TopBar() }, floatingActionButton = {
        AddFloatingButton()
    }, bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            SetUpNavigation(navController = navController)
        }
    }
}


@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.Black,
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(Icons.Filled.Menu, "", tint = Color.Black)
            }
        })
}

@Composable
fun AddFloatingButton() {
    FloatingActionButton(onClick = {

    }, backgroundColor = colorResource(id = R.color.orange), content = {
        Icon(painterResource(id = R.drawable.ic_sale), "")
    })
}

/*
setup navigation for 2 bottom tabs
 */
@Composable
fun SetUpNavigation(navController: NavHostController) {
    val tabStateHolder = HomeBottomBarStateHolder(
        rememberLazyListState(),
        rememberLazyListState()
    )
    NavHost(navController, startDestination = NavigationItem.BottomHomeProductList.route) {
        composable(NavigationItem.BottomHomeProductList.route) {
            LoadHomeProductsScreen(hiltViewModel(),tabStateHolder.productLazyListState )
        }
        composable(NavigationItem.BottomFavouriteProductList.route) {
            LoadFavouritesProductsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.BottomHomeProductList, NavigationItem.BottomFavouriteProductList
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.black), contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(icon = {
                Icon(
                    painterResource(id = item.icon),
                    contentDescription = stringResource(item.title)
                )
            },
                label = {
                    Text(stringResource(item.title))
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = false,
                //selected = item == selectedTab,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        /**
                         *  Pop up to the start destination of the graph to
                        avoid building up a large stack of destinations
                        on the back stack as users select items
                         */
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        /**
                         *  Avoid multiple copies of the same destination when
                        reselecting the same item
                         */
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}


/**
 * preview of widgets
 */
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    NavigateToHomeBottomBarScreen()
//}


