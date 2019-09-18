package com.example.ageone.External.Extensions.Realm

import com.example.ageone.Application.rxData
import com.example.ageone.Application.utils
import com.example.ageone.SCAG.Order
import com.example.ageone.SCAG.OrderUtiles
import com.example.ageone.SCAG.Product
import io.realm.Realm
import io.realm.RealmObject
import timber.log.Timber

fun add(some: RealmObject): Boolean {
    return try {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(some)
        realm.commitTransaction()
        true
    } catch (e: Exception) {
        Timber.e("$e")
        false
    }
}

fun Product.isAccess() = isFree || isIntro || rxData.isVip() || utils.realm.order.containMeditation(this) //TODO: meditation in orders


fun OrderUtiles.getAllMeditations() = getAllObjects().filter { order ->
    order.product != null
}

fun OrderUtiles.getAllSets() = getAllObjects().filter { order ->
    order.productSet != null
}

fun OrderUtiles.containMeditation(meditation: Product): Boolean {
    getAllMeditations().forEach { order ->
        if (order.product?.hashId == meditation.hashId) {
            return true
        }
    }
    return false
}