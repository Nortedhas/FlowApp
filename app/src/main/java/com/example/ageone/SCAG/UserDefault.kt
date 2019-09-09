package com.example.ageone.SCAG

import io.realm.Realm
import net.alexandroid.shpref.ShPref

object UserData {
    var phone: String
        get() = ShPref.getString("userPhone", "")
        set(value) = ShPref.put("userPhone", value)

    var email: String
        get() = ShPref.getString("userEmail", "")
        set(value) = ShPref.put("userEmail", value)

    var fullName: String
        get() = ShPref.getString("userFullName", "")
        set(value) = ShPref.put("userFullName", value)

    var fav: Announce?
        get() {
            val hash = ShPref.getString("userFav", "")
            return if (hash == "")
                null
            else
                Realm.getDefaultInstance().where(Announce::class.java).findFirst()

        }
        set(value) {
            value?.let { dog ->
                val hash = dog.hashId
                if (hash != "")
                    ShPref.put("userFav", hash)
            }
        }

    var likes: List<Announce>
        get() {
            val hashes = ShPref.getListOfStrings("userLikes")
            return if (hashes.isNullOrEmpty()) {
                emptyList()
            } else {
                val list = mutableListOf<Announce>()
                hashes.forEach{ hash ->
                    Realm.getDefaultInstance()
                        .where(Announce::class.java)
                        .equalTo("primaryKey", hash)
                        .equalTo("isExist", true)
                        .findFirst()?.let {announce ->
                            list.add(announce)
                        }
                }
                list
            }
        }
        set(list) {
            if (list.isNullOrEmpty()) {
                ShPref.putList("userLikes", emptyList<String>())
            } else {
                val hashes = mutableListOf<String>()
                for (announce in list) {
                    val hash = announce.hashId
                    if (hash != "") {
                        hashes.add(hash)
                    }
                }
                ShPref.putList("userLikes", hashes
                )
            }
        }

    var names: ArrayList<String>
        get() = ShPref.getListOfStrings("userNames")
        set(value) = ShPref.putList("userNames", value)
}