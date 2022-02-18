package com.example.chintanandroidpractical.network.service

import com.example.chintanandroidpractical.models.networkmodels.ResponseProducts
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    // webservice for fetching products
    @GET("{page_size}/{offset}/summaries?categoryIds=2")
    suspend fun fetchProducts(@Path("page_size") page_size: Int,@Path("offset") page: Int): ApiResponse<ResponseProducts>

    // sample URL for understanding
    //https://api.net-a-porter.com/NAP/GB/en/60/0/summaries?categoryIds=2 where 60 is page size and 0 is offset.

}