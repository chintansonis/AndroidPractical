package com.example.chintanandroidpractical.ui.screens.homeproductlist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.chintanandroidpractical.R
import com.example.chintanandroidpractical.models.NetworkState
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.models.onError
import com.example.chintanandroidpractical.models.onLoading
import com.example.chintanandroidpractical.models.onSuccess
import com.example.chintanandroidpractical.network.compose.NetworkImage
import com.example.chintanandroidpractical.ui.LoadEmptyView
import com.example.chintanandroidpractical.ui.LoadingProgressBarScreen
import com.example.chintanandroidpractical.utils.AppConstants


@Composable
fun LoadHomeProductsScreen(viewModel: LoadHomeProductViewModel, lazyListState: LazyListState) {
    val networkState: NetworkState by viewModel.productLoadingState
    val products by viewModel.productsList
    Surface(color = colorResource(id = R.color.light_gray), modifier = Modifier.fillMaxSize()) {
        networkState.onSuccess {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                state = lazyListState,
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(products.sortedBy { it.name }) { _, product ->
                    Log.d("System out", "it.id " + product.id)
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = MaterialTheme.colors.surface,
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(dimensionResource(id = R.dimen.padding_6dp))
                        //.clickable { onItemClicked(item.id) }
                    ) {
                        ConstraintLayout {
                            val (image, title, price, favourtieIcon) = createRefs()
                            var favourtieIconLiked by remember {
                                mutableStateOf(product.isFavouriteAdded)
                            }
                            var lines by remember { mutableStateOf(0) }
                            NetworkImage(
                                networkUrl = AppConstants.getImagePath(product = product),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(320.dp)
                                    .constrainAs(image) {
                                        top.linkTo(parent.top)
                                    },
                            )

                            Text(text = product.name,
                                style = MaterialTheme.typography.body2,
                                textAlign = TextAlign.Left,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(0.85f)
                                    .padding(horizontal = 8.dp, vertical = 8.dp)
                                    .constrainAs(title) {
                                        top.linkTo(image.bottom)
                                    },
                                onTextLayout = { res -> lines = res.lineCount })
                            for (i in lines..2) {
                                Text(" ", style = MaterialTheme.typography.h2)
                            }
                            Text(text = product.price.amount.toString(),
                                style = MaterialTheme.typography.body2,
                                textAlign = TextAlign.Left,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(0.85f)
                                    .padding(horizontal = 8.dp)
                                    .constrainAs(price) {
                                        top.linkTo(title.bottom)
                                    })
                            IconButton(onClick = {
                                val favoriteEntity = FavouriteSummary(0, product)
                                viewModel.insertFavoriteSummary(favoriteEntity)
                                favourtieIconLiked = !favourtieIconLiked
                            }, modifier = Modifier.constrainAs(favourtieIcon) {
                                top.linkTo(price.bottom)
                            }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (favourtieIconLiked)
                                            R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                                    ), ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    networkState.onLoading {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LoadingProgressBarScreen()
        }
    }
    networkState.onError {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LoadEmptyView(emptyViewMessage = R.string.no_products_found)
        }
    }
}

