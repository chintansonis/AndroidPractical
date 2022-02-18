package com.example.chintanandroidpractical.utils

import com.example.chintanandroidpractical.models.networkmodels.Price
import com.skydoves.whatif.whatIf


object Extensions {
    /*
    function for provide price of product
     */
    fun returnPrice(price: Price): Int {
        var updatedPrice = 0
        // we are checking if discuount available
        val checkValue: Boolean = price.discountPercent == 0

        //by using this function, we will check if discount available
        whatIf(given = checkValue, whatIf = {
            // if not discount available, simply fetch value by dividing divisor which is 100
            updatedPrice = (price.amount / price.divisor)
        }, whatIfNot = {
            /**
             *if discount available, simply fetch amount by below formula
             *    price= (amount/100)*discount/100
             */
            updatedPrice = ((price.amount / price.divisor)) * price.discountPercent / price.divisor
        })

        return updatedPrice
    }

    // sample object for understanding
    /*"price": {
        "discountPercent": 60,
        "divisor": 100,
        "originalAmount": 19900,
        "amount": 7960,
        "currency": "GBP"
    }*/
}