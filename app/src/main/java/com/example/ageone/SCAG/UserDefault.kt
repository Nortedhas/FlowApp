package com.example.ageone.SCAG

import com.example.ageone.External.HTTP.API.Dog
import io.realm.Realm
import net.alexandroid.shpref.ShPref

object UserData {
    var phone: String
        get() = ShPref.getString("userPhone", "")
        set(value) = ShPref.put("userPhone", value)

    var fullName: String
        get() = ShPref.getString("userFullName", "")
        set(value) = ShPref.put("userFullName", value)

    var fav: Dog?
        get() {
            val hash = ShPref.getString("userFav", "")
            return if (hash == "")
                null
            else
                Realm.getDefaultInstance().where(Dog::class.java).findFirst()

        }
    set(value) {
        value?.let { dog ->
            val hash = dog.primaryKey
            if (hash != "")
                ShPref.put("userFav", hash)
        }
    }
}