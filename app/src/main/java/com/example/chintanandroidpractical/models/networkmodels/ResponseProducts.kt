package com.example.chintanandroidpractical.models.networkmodels

import androidx.compose.runtime.Immutable
import com.example.chintanandroidpractical.models.NetworkResponseModel
import com.example.chintanandroidpractical.models.entities.Summary

@Immutable
data class ResponseProducts(
    val listInfo: ListInfo,
    val summaries: List<Summary>
): NetworkResponseModel

// sample response for understanding.
// Parameters we used for displaying data: name and price, image objects
//
// {
//    "summaries": [
//    {
//        "name": "+ NET SUSTAIN Opera off-the-shoulder lace-trimmed linen midi dress ",
//        "visible": true,
//        "saleableStandardSizeIds": [
//        "00003_XS_Clothing",
//        "00004_S_Clothing",
//        "00005_M_Clothing",
//        "00006_L_Clothing",
//        "00007_XL_Clothing"
//        ],
//        "price": {
//        "currency": "GBP",
//        "divisor": 100,
//        "amount": 24500
//    },
//        "leafCategoryIds": [
//        6040,
//        5670,
//        27194,
//        37101,
//        41386
//        ],
//        "onSale": false,
//        "analyticsKey": "+ NET SUSTAIN Opera off-the-shoulder lace-trimmed linen midi dress ",
//        "id": 1314933,
//        "brandId": 3624,
//        "colourIds": [
//        43
//        ],
//        "images": {
//        "shots": [
//        "in",
//        "ou",
//        "bk",
//        "cu",
//        "e1",
//        "e2"
//        ],
//        "sizes": [
//        "dl",
//        "l",
//        "m",
//        "m2",
//        "mt",
//        "mt2",
//        "pp",
//        "s",
//        "sl",
//        "xl",
//        "xs",
//        "xxl"
//        ],
//        "mediaType": "image/jpeg",
//        "urlTemplate": "{{scheme}}//cache.net-a-porter.com/images/products/1314933/1314933_{{shot}}_{{size}}.jpg"
//    },
//        "badges": [
//        "In_Stock"
//        ]
//    }
//    ],
//    "listInfo": {
//    "limit": 1,
//    "offset": 0,
//    "total": 12652
//}
