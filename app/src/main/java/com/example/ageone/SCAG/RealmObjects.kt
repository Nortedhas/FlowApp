package com.example.ageone.SCAG

import com.example.ageone.Application.utils
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.json.JSONObject
import timber.log.Timber

enum class DataBase{
    Product, Announce, ProductSet, Rune, Image;

    companion object DataObjects {
        var url: String = "http://45.141.102.83"
        var headers = mutableMapOf("x-access-token" to utils.variable.token)
    }
}

open class Announce(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var txtInfo: String = "",
    open var link: String = ""
): RealmObject()

open class Product(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var txtInfo: String = "",
    open var isIntro: Boolean = false,
    open var isPopular: Boolean = false,
    open var isQuickStart: Boolean = false,
    open var isFree: Boolean = false,
    open var isExist: Boolean = false
): RealmObject()

open class ProductSet(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var txtInfo: String = "",
    open var image: Image? = null,
    open var rune: Rune? = null,
    open var tracks: RealmList<Product> = RealmList(),
    open var intro: Product? = null,
    open var isPopular: Boolean = false,
    open var isQuickStart: Boolean = false,
    open var isFree: Boolean = false,
    open var isExist: Boolean = false,
    open var updated: Int = 0,
    open var created: Int = 0
 ): RealmObject()


open class Image(
    @PrimaryKey open var primaryKey: String = "",
    open var original: String = "",
    open var preview: String = "",
    open var isExist: Boolean = false,
    open var updated: Int = 0,
    open var created: Int = 0
): RealmObject()

open class Rune(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var serialNum: Int = 0,
    open var isExist: Boolean = false,
    open var updated: Int = 0,
    open var created: Int = 0
): RealmObject()

class Model{
    val realm = Realm.getDefaultInstance()

    fun create(json: JSONObject, type: DataBase): RealmObject =
        when (type) {
            DataBase.Announce -> {
                json.createAnnounce()
            }
            DataBase.Product -> {
                json.createProduct()
            }
            DataBase.Image -> {
                json.createImage()
            }
            DataBase.Rune -> {
                json.createRune()
            }
            DataBase.ProductSet -> {
                json.createProductSet()
            }
        }


    fun add(some: RealmObject): Boolean {
        return try {
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


fun JSONObject.createAnnounce(): Announce{
    val some = Announce()
    some.primaryKey = optString("hashId","")
    some.name = optString("name","")
    some.txtInfo = optString("txtInfo","")
    some.link = optString("link","")
    return some
}

fun JSONObject.createProduct(): Product {
    val some = Product()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.txtInfo = optString("txtInfo", "")
    some.isIntro = optBoolean("isIntro", false)
    some.isPopular = optBoolean("isPopular", false)
    some.isQuickStart = optBoolean("isQuickStart", false)
    some.isFree = optBoolean("isFree", false)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.createRune(): Rune {
    val some = Rune()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.serialNum = optInt("serialNum", 0)
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.createImage(): Image {
    val some = Image()
    some.primaryKey = optString("hashId", "")
    some.original = optString("original", "")
    some.preview = optString("preview", "")
    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    some.isExist = optBoolean("isExist", false)
    return some
}

fun JSONObject.createProductSet(): ProductSet {
    val some = ProductSet()
    some.primaryKey = optString("hashId", "")
    some.name = optString("name", "")
    some.txtInfo = optString("txtInfo", "")
    some.isPopular = optBoolean("isPopular", false)
    some.isQuickStart = optBoolean("isQuickStart", false)
    some.isFree = optBoolean("isFree", false)
    some.isExist = optBoolean("isExist", false)

    optJSONObject("tracks")?.let { products ->
        for (i in 0 until products.length()) {
            some.tracks.add(
                products.getJSONObject("$i").createProduct()
            )
        }
    }

    optJSONObject("intro")?.let { product ->
        some.intro = product.createProduct()
    }

    optJSONObject("image")?.let { image ->
        some.image = image.createImage()
    }

    optJSONObject("rune")?.let { rune ->
        some.rune = rune.createRune()
    }

    some.updated = optInt("updated", 0)
    some.created = optInt("created", 0)
    return some
}