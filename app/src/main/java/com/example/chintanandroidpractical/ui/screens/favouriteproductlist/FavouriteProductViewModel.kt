package com.example.chintanandroidpractical.ui.screens.favouriteproductlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.models.entities.Summary
import com.example.chintanandroidpractical.repository.LocalDataSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteProductViewModel @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : ViewModel() {

    private val _favouriteProductList = MutableStateFlow(listOf<FavouriteSummary>())
    val favouriteProductList: StateFlow<List<FavouriteSummary>> get() = _favouriteProductList


    init {
        getFavouriteProducts()
    }

    fun getFavouriteProducts() {
        viewModelScope.launch(Dispatchers.Default) {
            _favouriteProductList.emit(localDataSourceRepository.getFavoriteSummary())
        }
    }
    /**
     * delete the favourite item in FavouriteSummary table
     */
    fun deleteFavoriteSummary(favouriteSummary: FavouriteSummary) =
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.deleteFavoriteSummary(favouriteSummary)
        }
    /*
        update isFavourite value to summary table to reflect the main UI
         */
    fun updateFavoriteDataInSummaryTable(summary: Summary) = viewModelScope.launch(Dispatchers.IO) {
        val summaryObject = localDataSourceRepository.getSummaryObject(summary.id)
        summaryObject.isFavouriteAdded = !summaryObject.isFavouriteAdded
        localDataSourceRepository.updateSummaryObject(summaryObject)
    }

}