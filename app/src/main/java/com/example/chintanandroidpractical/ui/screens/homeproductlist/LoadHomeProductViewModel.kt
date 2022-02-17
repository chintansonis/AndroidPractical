package com.example.chintanandroidpractical.ui.screens.homeproductlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.chintanandroidpractical.models.NetworkState
import com.example.chintanandroidpractical.models.entities.Summary
import com.example.chintanandroidpractical.repository.ProductSummaryRepository
import com.example.chintanandroidpractical.utils.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadHomeProductViewModel @Inject constructor(
    val imageLoader: ImageLoader, private val productSummaryRepository: ProductSummaryRepository
) : ViewModel() {

    private val _selectedTab: MutableState<NavigationItem> =
        mutableStateOf(NavigationItem.BottomHomeProductList)
    val selectedTab: State<NavigationItem> get() = _selectedTab

    val _productLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
    val productLoadingState: State<NetworkState> get() = _productLoadingState


    val productsList: State<MutableList<Summary>> = mutableStateOf(mutableListOf())
    val productPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(0)


    private val newProductFlow = productPageStateFlow.flatMapLatest {
        _productLoadingState.value = NetworkState.LOADING
        productSummaryRepository.loadProductList(limit = it,
            success = { _productLoadingState.value = NetworkState.SUCCESS },
            error = { _productLoadingState.value = NetworkState.ERROR })
    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)


    init {
        viewModelScope.launch(Dispatchers.IO) {
            newProductFlow.collectLatest {
                productsList.value.addAll(it)
            }
        }

    }

    fun selectTab(tab: NavigationItem) {
        _selectedTab.value = tab
    }

    fun fetchNextProductPage() {
        if (productLoadingState.value != NetworkState.LOADING) {
            productPageStateFlow.value++
        }
    }
}
