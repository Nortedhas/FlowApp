package com.example.ageone.Models

import android.content.DialogInterface
import com.example.ageone.Application.api
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.SCAG.Product
import com.example.ageone.SCAG.UserData.vipAccessTo
import timber.log.Timber
import kotlin.properties.Delegates

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

    // MARK: selected in Pleer meditation

    var currentMeditation: Product? = null

    // MARK: playing meditation in background

    var isMeditationEnd: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        if (newValue) {
            RxBus.publish(RxEvent.EventMediaPlayerEnd())
        }
    }

    var duration: Long by Delegates.observable(0L) { property, oldValue, newValue ->
        RxBus.publish(RxEvent.EventChangeDuration(newValue))
    }

    var currentTime: Long by Delegates.observable(0L) { property, oldValue, newValue ->
        RxBus.publish(RxEvent.EventChangeCurrentTime(newValue))
    }
}

