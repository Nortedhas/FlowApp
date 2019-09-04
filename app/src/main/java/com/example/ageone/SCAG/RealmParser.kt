package com.example.ageone.SCAG

import io.realm.Realm
import io.realm.RealmObject
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber

class RealmParser{

    fun parseToDataBase(array: JSONArray, type: RealmObjects.DataBase) {
        for (i in 0 until array.length()) {
            val json = array[i] as JSONObject

            val obj = when (type) {
                RealmObjects.DataBase.Announce -> {
                    json.parseAnnounce()
                }
                RealmObjects.DataBase.Product -> {
                    json.parseProduct()
                }
                RealmObjects.DataBase.Image -> {
                    json.parseImage()
                }
                RealmObjects.DataBase.Rune -> {
                    json.parseRune()
                }
                RealmObjects.DataBase.ProductSet -> {
                    json.parseProductSet()
                }
                RealmObjects.DataBase.Audio -> {
                    json.parseAudio()
                }
                RealmObjects.DataBase.Purpose -> {
                    json.parsePurpose()
                }
            }

            try {
                add(obj)
            } catch (e: java.lang.Exception) {
                Timber.e("$e")
            }

        }
    }



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
}

class Parser {
    fun parseAnyObject(json: JSONObject, type: RealmObjects.DataBase) {
        json.optJSONArray(type.name)?.let{array ->
            val realmParser = RealmParser()
            realmParser.parseToDataBase(array, type)
        }
    }
}


fun JSONObject.parseAnnounce(): Announce {
    val some = Announce()
    optJSONObject("image")?.let { image ->
        some.image = image.parseImage()
    }
    some.type = optString("__type", "")
    some.primaryKey = optString("hashId","")
    some.name = optString("name","")
    some.txtInfo = optString("txtInfo","")
    some.link = optString("link","")
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.parseProduct(): Product {
    val some = Product()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.txtInfo = optString("txtInfo", "")
    optJSONObject("audio")?.let { audio ->
        some.audio = audio.parseAudio()
    }
    optJSONObject("purpose")?.let { purposes ->
        for (i in 0 until purposes.length()) {
            some.purpose.add(
                purposes.getJSONObject("$i").parsePurpose()
            )
        }
    }
    some.duration = optString("__duration", "")
    some.isIntro = optBoolean("isIntro", false)
    some.isPopular = optBoolean("isPopular", false)
    some.isQuickStart = optBoolean("isQuickStart", false)
    some.isFree = optBoolean("isFree", false)
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

private fun JSONObject.parsePurpose(): Purpose {
    val some = Purpose()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.serialNum = optInt("serialNum", 0)
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.parseRune(): Rune {
    val some = Rune()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.serialNum = optInt("serialNum", 0)
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.parseAudio(): Audio {
    val some = Audio()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.file = optString("file", "")
    some.sourceName = optString("file", "")
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.parseImage(): Image {
    val some = Image()
    some.primaryKey = optString("hashId", "")
    some.original = optString("original", "")
    some.preview = optString("preview", "")
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.parseProductSet(): ProductSet {
    val some = ProductSet()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.txtInfo = optString("txtInfo", "")
    some.isFree = optBoolean("isFree", false)
    some.isExist = optBoolean("isExist", false)

    optJSONObject("tracks")?.let { products ->
        for (i in 0 until products.length()) {
            some.tracks.add(
                products.getJSONObject("$i").parseProduct()
            )
        }
    }

    optJSONObject("intro")?.let { product ->
        some.intro = product.parseProduct()
    }

    optJSONObject("image")?.let { image ->
        some.image = image.parseImage()
    }

    optJSONObject("rune")?.let { rune ->
        some.rune = rune.parseRune()
    }

    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    return some
}