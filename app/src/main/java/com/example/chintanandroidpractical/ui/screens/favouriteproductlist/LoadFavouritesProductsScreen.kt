package com.example.chintanandroidpractical.ui.screens.favouriteproductlist


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.network.compose.NetworkImage
import com.example.chintanandroidpractical.ui.LoadEmptyView
import com.example.chintanandroidpractical.utils.AppConstants
import com.example.chintanandroidpractical.utils.Extensions

@Composable
fun LoadFavouritesProductsScreen(
    viewModel: FavouriteProductViewModel, favouriteListState: LazyListState
) {
    // data holder from viewmodel
    val favouriteProducts = viewModel.favouriteProductList.collectAsState()

    // used to load data on priority when screen display
    LaunchedEffect(key1 = stringResource(R.string.launch_favourites)) {
        viewModel.getFavouriteProducts()
    }


    if (favouriteProducts.value.isEmpty()) {
        // if no data display empty view
        LoadEmptyView(emptyViewMessage = R.string.no_favourites_found)
    } else {
        // background of screen
        Surface(color = colorResource(id = R.color.light_gray), modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                state = favouriteListState,
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(favouriteProducts.value.sortedBy { it.summary.name }) { _, favouriteEntity ->
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
                            val (image, title, price, favouriteIcon) = createRefs()
                            NetworkImage(
                                networkUrl = AppConstants.getImagePath(product = favouriteEntity.summary),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen.padding_320dp))
                                    .constrainAs(image) {
                                        top.linkTo(parent.top)
                                    },
                            )

                            Text(text = favouriteEntity.summary.name,
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

                            Text(text = stringResource(id = R.string.currency) + Extensions.returnPrice(
                                favouriteEntity.summary.price
                            ).toString(),
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

                            IconButton(onClick = {
                                val favoriteEntity =
                                    FavouriteSummary(favouriteEntity.id, favouriteEntity.summary)
                                viewModel.deleteFavoriteSummary(favoriteEntity)

                                viewModel.updateFavoriteDataInSummaryTable(favouriteEntity.summary)
                            }, modifier = Modifier.constrainAs(favouriteIcon) {
                                top.linkTo(price.bottom)
                                bottom.linkTo(parent.bottom)
                            }) {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.ic_baseline_favorite_24
                                    ), ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

