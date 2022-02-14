package com.example.chintanandroidpractical.ui.screens.homeproductlist


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoadHomeProductsScreen() {
    Surface(color = colorResource(id = R.color.light_gray), modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 140.dp), contentPadding = PaddingValues(4.dp)
        ) {
            items(10) {
                ProductItemGrid()
            }
        }
    }
}

/*
item view for product list
 */
@Composable
fun ProductItemGrid() {
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
                text = "Cape-effect intarsia wool-blend midi dress",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_8dp),
                    vertical = dimensionResource(id = R.dimen.padding_6dp)
                )
            )
            Text(
                text = "£ 90",
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
