package com.example.chintanandroidpractical.network.service

import com.example.chintanandroidpractical.models.networkmodels.ResponseProducts
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    //https://api.net-a-porter.com/NAP/GB/en/60/0/summaries?categoryIds=2
    @GET("{page_size}/{offset}/summaries?categoryIds=2")
    suspend fun fetchProducts(@Path("page_size") page_size: Int,@Path("offset") page: Int): ApiResponse<ResponseProducts>

}