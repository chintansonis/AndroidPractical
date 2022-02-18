package com.example.chintanandroidpractical.ui

import androidx.compose.foundation.lazy.LazyListState


// preserving the state of list
data class HomeBottomBarStateHolder(
  val productLazyListState: LazyListState,
  val favouriteListState: LazyListState
)
