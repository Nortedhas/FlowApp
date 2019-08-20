package com.example.ageone.Models.User

import net.alexandroid.shpref.ShPref

//object?
object user {

    var hashId: String
        get() = ShPref.getString("userHashId", "")
        set(value) = ShPref.put("userHashId", value)

    var fcmToken: String
        get() = ShPref.getString("userFcmToken", "")
        set(value) = ShPref.put("userFcmToken", value)

    var phone: String
        get() = ShPref.getString("userPhone", "")
        set(value) = ShPref.put("userPhone", value)

    var fullName: String
        get() = ShPref.getString("userFullName", "")
        set(value) = ShPref.put("userFullName", value)

    var isAuthorized: Boolean
        get() = ShPref.getBoolean("userIsAuthorized", false)
        set(value) = ShPref.put("userIsAuthorized", value)
}