package com.example.chintanandroidpractical.repository


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


    // load products. methods using sandwhich library for lighter and smartly managing request response
    @WorkerThread
    fun loadProductList(limit: Int, success: () -> Unit, error: () -> Unit) = flow {
        // fetching products from DB
        var products = summaryDao.getSummaryList()

        if (products.isEmpty()) {
            val response = productService.fetchProducts(AppConstants.PAGING_SIZE, limit)
            response.suspendOnSuccess {
                products = data.summaries
                // inserting new data into db
                summaryDao.insertSummaryList(products)
                emit(products)
                success()
            }.onError {
                error()
            }.onException { error() }
        } else {
            emit(products)
        }
    }.onCompletion { success() }.flowOn(Dispatchers.IO)


}
