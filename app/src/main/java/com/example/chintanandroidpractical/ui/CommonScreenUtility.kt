package com.example.chintanandroidpractical.ui


import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chintanandroidpractical.R

@Composable
fun LoadEmptyView(@StringRes emptyViewMessage: Int) {
    Surface(color = colorResource(id = R.color.light_gray), modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sad_smily),
                contentDescription = stringResource(id = emptyViewMessage),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = emptyViewMessage),
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun LoadingProgressBarScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

/*
show all previews of widgets
 */
@Preview(showBackground = true)
@Composable
fun LoadEmptyViewPreview() {
    LoadEmptyView( R.string.no_products_found)
}

@Preview()
@Composable
fun LoadLoadingProgressBarPreview() {
    LoadingProgressBarScreen()
}