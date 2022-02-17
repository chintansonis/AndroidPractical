package com.example.chintanandroidpractical.utils

import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow

inline fun <T> LazyGridScope.paging(
  items: List<T>,
  currentIndexFlow: StateFlow<Int>,
  threshold: Int = 4,
  pageSize: Int = AppConstants.PAGING_SIZE,
  crossinline fetch: () -> Unit,
  crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) {
  val currentIndex = currentIndexFlow.value

  itemsIndexed(items) { index, item ->

    itemContent(item)

    if ((index + threshold + 1) >= pageSize * (currentIndex - 1)) {
      fetch()
    }
  }
}
