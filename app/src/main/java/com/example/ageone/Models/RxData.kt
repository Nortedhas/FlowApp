package com.example.ageone.Models

import android.content.DialogInterface
import com.example.ageone.Application.api
import com.example.ageone.SCAG.Product
import com.example.ageone.SCAG.ProductSet
import com.example.ageone.SCAG.UserData.vipAccessTo
import timber.log.Timber

class RxData {
    fun isVip() : Boolean =
        vipAccessTo > (System.currentTimeMillis() / 1000)

    val payVariants = arrayOf(//TODO get prices
        "На 48 часов (99 р.)",
        "На месяц (299 р.)",
        "На год (1 299 р.)"
        )

    fun createPayVariantCallback(hashId: String, isSet: Boolean): (DialogInterface, Int) -> (Unit) {

        return { dialog, which ->
            when (which) {
                0 -> {
                    Timber.i("99 pay")
                    if (isSet) {
                        api.createOrder(hashId, "")
                    } else {
                        api.createOrder("", hashId)
                    }

                }

                1 -> {
                    Timber.i("299 pay")

                }

                2 -> {
                    Timber.i("1299 pay")

                }
            }
        }
    }


    var currentMeditation: Product? = null
}

