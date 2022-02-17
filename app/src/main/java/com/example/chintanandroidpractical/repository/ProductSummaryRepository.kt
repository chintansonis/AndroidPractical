package com.example.chintanandroidpractical.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.chintanandroidpractical.database.SummaryDao
import com.example.chintanandroidpractical.network.service.ProductService
import com.example.chintanandroidpractical.utils.AppConstants
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion


class ProductSummaryRepository constructor(
    private val productService: ProductService, private val summaryDao: SummaryDao
) : Repository {

    init {
        Log.d("Injected repository", "Injection ProductSummaryRepository")
    }

    @WorkerThread
    fun loadProductList(limit: Int, success: () -> Unit, error: () -> Unit) = flow {
        var products = summaryDao.getSummaryList()
        //var products = summaryDao.getSummaryList(limit)
        if (products.isEmpty()) {
            val response = productService.fetchProducts(AppConstants.PAGING_SIZE,limit)
            response.suspendOnSuccess {
                products = data.summaries
//                products.forEach {
//                    it.limit = limit
//                    Log.d("System out", "productPageStateFlow.value limit $limit")
//                }
                summaryDao.insertSummaryList(products)
                emit(products)
            }.onError {
                error()
            }.onException { error() }
        } else {
            emit(products)
        }
    }.onCompletion { success() }.flowOn(Dispatchers.IO)
}
