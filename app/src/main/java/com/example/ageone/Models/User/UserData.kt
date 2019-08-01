package com.example.ageone.Models.User

import net.alexandroid.shpref.ShPref

class UserData {
    //TODO: photo
    var hashId: String
        get() = ShPref.getString("userHashId", "")
        set(value) = ShPref.put("userHashId", value)


}