package com.example.ageone.Models

import androidx.lifecycle.LiveData
import io.realm.*
import io.realm.annotations.PrimaryKey
import timber.log.Timber


open class Meditation(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String ="",
    open var describe: String = ""
): RealmObject()


class RealmLiveData<T : RealmModel>(private val results: RealmResults<T>) :
    LiveData<RealmResults<T>>() {
    private val listener =
        RealmChangeListener<RealmResults<T>> { results -> value = results }

    override fun onActive() {
        results.addChangeListener(listener)
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }
}

fun <T: RealmModel> RealmResults<T>.asLiveData() = RealmLiveData(this)

fun addMeditation(realm: Realm, meditation: Meditation): Boolean {
    try {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(meditation)
        realm.commitTransaction()
        return true
    } catch (e: Exception) {
        println(e)
        return false
    }
}

fun getAllMeditation(realm: Realm): RealmResults<Meditation> {
    return realm.where(Meditation::class.java).findAll()
}

class KartDao(val realm: Realm) {

    fun addToKart(meditation: Meditation) {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(meditation)
            realm.commitTransaction()
            Timber.i("Add user")
        } catch (e: Exception) {
            println(e)
        }
    }

    fun getKart(): LiveData<RealmResults<Meditation>> {
        return realm.where(Meditation::class.java).findAllAsync().asLiveData()
    }

    fun getAllMeditation(): RealmResults<Meditation> {
        return realm.where(Meditation::class.java).findAllAsync()
    }

}