package com.example.chintanandroidpractical.utils

import com.example.chintanandroidpractical.models.entities.Summary

object AppConstants {
    const val BASE_URL = "https://api.net-a-porter.com/NAP/GB/en/"
    const val IMAGE_BASE_URL = "https://cache.net-a-porter.com/images/products/"
    const val PAGING_SIZE = 80
    const val DATABASE_NAME = "AndroidPractical.db"

    /**
     * function for preparing image path, image url used static shots and images size from image object. find below object for understanding
     */
    @JvmStatic
    fun getImagePath(product: Summary): String {
        return IMAGE_BASE_URL + "${product.id}"+"/"+"${product.id}"+"_"+ product.images.shots[0]+"_"+ product.images.sizes[0]+".jpg"
    }

    /*"images": {
        "shots": [
        "in", 1st element for getting in type
        "ou",
        "bk",
        "cu",
        "e1",
        "e2"
        ],
        "sizes": [
        "dl", 2nd element for getting in size
        "l",
        "m",
        "m2",
        "mt",
        "mt2",
        "pp",
        "s",
        "sl",
        "xl",
        "xs",
        "xxl"
        ],
        "mediaType": "image/jpeg",
        "urlTemplate": "{{scheme}}//cache.net-a-porter.com/images/products/1314933/1314933_{{shot}}_{{size}}.jpg"*/
    }