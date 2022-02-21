package com.example.chintanandroidpractical.ui.screens.homeproductlist


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.chintanandroidpractical.models.NetworkState
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.models.entities.Summary
import com.example.chintanandroidpractical.repository.LocalDataSourceRepository
import com.example.chintanandroidpractical.repository.ProductSummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadHomeProductViewModel @Inject constructor(
    val imageLoader: ImageLoader,
    private val productSummaryRepository: ProductSummaryRepository,
    private val localDataSourceRepository: LocalDataSourceRepository
) : ViewModel() {


    val _productLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
    val productLoadingState: State<NetworkState> get() = _productLoadingState

    val productPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(0)


    // fetching data from webservice and manage it to db
    val productListFlow = productPageStateFlow.flatMapLatest {
        _productLoadingState.value = NetworkState.LOADING
        productSummaryRepository.loadProductList(limit = it,
            success = { _productLoadingState.value = NetworkState.SUCCESS },
            error = { _productLoadingState.value = NetworkState.ERROR })
    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    //emitting product list
    fun fetchproductList() = productPageStateFlow.tryEmit(1)


    /**
     * insert the favourite item in FavouriteSummary table
     */
    fun insertFavoriteSummary(favouriteSummary: FavouriteSummary) =
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.insertFavoriteSummary(favouriteSummary)
        }

    /**
     * delete the favourite item in FavouriteSummary table
     */
    fun deleteFavoriteSummary(favouriteSummary: FavouriteSummary) =
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.deleteFavoriteSummary(favouriteSummary)
        }

    /*
    * update isFavourite value to summary table to reflect the main UI
     */
    fun updateFavoriteDataInSummaryTable(summary: Summary) = viewModelScope.launch(Dispatchers.IO) {
        val summaryObject = localDataSourceRepository.getSummaryObject(summary.id)
        summaryObject.isFavouriteAdded = !summaryObject.isFavouriteAdded
        localDataSourceRepository.updateSummaryObject(summaryObject)
    }
}
