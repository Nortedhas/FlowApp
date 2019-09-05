package com.example.ageone.SCAG

import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber

object RealmUtilities {

    val announce = AnnounceUtiles()
}

class AnnounceUtiles {
    fun getObjectById(id: String): Announce? =
        try {
            Realm.getDefaultInstance()
                .where(Announce::class.java)
                .equalTo("primaryKey", id)
                .equalTo("isExist", true)
                .findFirst()
        } catch (e: Exception) {
            Timber.e("$e")
            null
        }

    fun getAllObjects(): RealmResults<Announce> =
        try {
            Realm.getDefaultInstance()
                .where(Announce::class.java)
                .equalTo("isExist", true)
                .findAll()
        } catch (e: Exception) {
            Timber.e("$e")
            Realm.getDefaultInstance()
                .where(Announce::class.java)
                .alwaysFalse()
                .findAll()
        }
}
