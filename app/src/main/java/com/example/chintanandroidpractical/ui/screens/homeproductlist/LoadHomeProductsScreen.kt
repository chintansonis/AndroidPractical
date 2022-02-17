package com.example.chintanandroidpractical.ui.screens.homeproductlist


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chintanandroidpractical.R
import com.example.chintanandroidpractical.models.NetworkState
import com.example.chintanandroidpractical.models.entities.Summary
import com.example.chintanandroidpractical.models.onLoading
import com.example.chintanandroidpractical.models.onSuccess
import com.example.chintanandroidpractical.ui.LoadingProgressBarScreen
import com.example.chintanandroidpractical.utils.paging


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
                itemsIndexed(products) { index, destination ->
                    Log.d("System out","it.id "+destination.id)
                    ProductItemGrid(
                        product = destination
                    )
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
    }
}

/*
item view for product list
 */
@Composable
fun ProductItemGrid(product: Summary) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_6dp))
        //.clickable { onItemClicked(item.id) }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = colorResource(id = R.color.white))
                .fillMaxWidth()
//                .clickable {
//                    navController.navigate("details/$index")
//                }
        ) {
            Image(
                painter = painterResource(R.drawable.images),
                contentDescription = stringResource(R.string.dummy_product),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = product.name,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_8dp),
                    vertical = dimensionResource(id = R.dimen.padding_6dp)
                )
            )
            Text(
                text = product.price.amount.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_8dp))
            )
            Text(
                text = "2 colors available",
                color = Color.Black,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_8dp),
                    vertical = dimensionResource(id = R.dimen.padding_4dp)
                )
            )
        }
    }
}
