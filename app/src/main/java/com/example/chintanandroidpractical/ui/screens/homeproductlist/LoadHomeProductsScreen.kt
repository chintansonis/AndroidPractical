package com.example.chintanandroidpractical.ui.screens.homeproductlist
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
import androidx.compose.ui.res.stringResource
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
import com.example.chintanandroidpractical.utils.Extensions.returnPrice


@Composable
fun LoadHomeProductsScreen(viewModel: LoadHomeProductViewModel, lazyListState: LazyListState) {
    // data holder from viewmodel
    val networkState: NetworkState by viewModel.productLoadingState
    val products by viewModel.productsList

    // background of screen
    Surface(color = colorResource(id = R.color.light_gray), modifier = Modifier.fillMaxSize()) {
        networkState.onSuccess {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                state = lazyListState,
                contentPadding = PaddingValues(4.dp)
            ) {
                // products displayed by sorting name
                itemsIndexed(products.sortedBy { it.name }) { _, product ->
                    // inflating item of lazyvertical Grid which is subsitition of recyclerview
                    Card(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_8dp)),
                        backgroundColor = MaterialTheme.colors.surface,
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.padding_480dp))
                            .padding(dimensionResource(id = R.dimen.padding_6dp))
                    ) {
                        ConstraintLayout {
                            //references of views
                            val (image, title, price, favouriteIcon) = createRefs()
                            // preserve the click state of favourites
                            var favouriteIconLiked by remember {
                                mutableStateOf(product.isFavouriteAdded)
                            }
                            NetworkImage(
                                networkUrl = AppConstants.getImagePath(product = product),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen.padding_320dp))
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
                                    .padding(horizontal = dimensionResource(id = R.dimen.padding_8dp), vertical = dimensionResource(id = R.dimen.padding_8dp))
                                    .constrainAs(title) {
                                        top.linkTo(image.bottom)
                                    })
                            Text(text = stringResource(id = R.string.currency) + returnPrice(product.price).toString(),
                                style = MaterialTheme.typography.body2,
                                textAlign = TextAlign.Left,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(0.85f)
                                    .padding(horizontal = dimensionResource(id = R.dimen.padding_8dp))
                                    .constrainAs(price) {
                                        bottom.linkTo(price.bottom)
                                    })
                            // fav icons click management
                            IconButton(onClick = {
                                /**
                                 * updating product in main summary db table for preserving values for favourites, using it we can repopulate favourite items in main product list
                                 */
                                viewModel.updateFavoriteDataInSummaryTable(product)

                                // update existing field for UI
                                favouriteIconLiked = !favouriteIconLiked

                                /**
                                 * based on user added product as favorite, we insert or delete items from favorite table. it would reflect in favoriteList as well
                                 */
                                val favoriteEntity = FavouriteSummary(product.id, product)
                                if (favouriteIconLiked) {
                                    viewModel.insertFavoriteSummary(favoriteEntity)
                                } else {
                                    viewModel.deleteFavoriteSummary(favoriteEntity)
                                }

                                /**
                                 * updating existing object value in UI to preserve it on scroll
                                 */
                                product.isFavouriteAdded = favouriteIconLiked

                            }, modifier = Modifier.constrainAs(favouriteIcon) {
                                top.linkTo(price.bottom)
                                bottom.linkTo(parent.bottom)
                            }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (favouriteIconLiked)
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

    // show Loading screen on loading data
    networkState.onLoading {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LoadingProgressBarScreen()
        }
    }

    // show Error screen on loading data
    networkState.onError {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LoadEmptyView(emptyViewMessage = R.string.no_products_found)
        }
    }
}



