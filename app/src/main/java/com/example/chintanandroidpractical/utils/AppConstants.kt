package com.example.chintanandroidpractical.utils

import com.example.chintanandroidpractical.models.entities.Summary

object AppConstants {
    const val BASE_URL = "https://api.net-a-porter.com/NAP/GB/en/"
    const val IMAGE_BASE_URL = "https://cache.net-a-porter.com/images/products/"
    const val PAGING_SIZE = 80
    const val DATABASE_NAME = "AndroidPractical.db"

    @JvmStatic
    fun getImagePath(product: Summary): String {
        return IMAGE_BASE_URL + "${product.id}"+"/"+"${product.id}"+"_"+ product.images.shots[0]+"_"+ product.images.sizes[0]+".jpg"
    }


}