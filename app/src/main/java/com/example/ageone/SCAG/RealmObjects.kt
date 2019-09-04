package com.example.ageone.SCAG

import com.example.ageone.Application.utils
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class RealmObjects {
    enum class DataBase {
        Product, Announce, ProductSet, Rune, Image, Audio, Purpose;

        companion object DataObjects {
            var url: String = "http://45.141.102.83"
            var headers = mutableMapOf("x-access-token" to utils.variable.token)
        }
    }
}


open class Announce(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var txtInfo: String = "",
    open var image: Image? = null,
    open var link: String = "",
    open var type: String = "",
    open var isExist: Boolean = false,
    open var updated: Int = 0,
    open var created: Int = 0
): RealmObject()

open class Product(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var txtInfo: String = "",
    open var isIntro: Boolean = false,
    open var isPopular: Boolean = false,
    open var isQuickStart: Boolean = false,
    open var isFree: Boolean = false,
    open var isExist: Boolean = false,
    open var image: Image? = null,
    open var audio: Audio? = null,
    open var purpose: RealmList<Purpose> = RealmList(),
    open var duration: String = "",
    open var updated: Int = 0,
    open var created: Int = 0
): RealmObject()

open class ProductSet(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var txtInfo: String = "",
    open var image: Image? = null,
    open var rune: Rune? = null,
    open var tracks: RealmList<Product> = RealmList(),
    open var intro: Product? = null,
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

open class Audio(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var file: String = "",
    open var sourceName: String = "",
    open var isExist: Boolean = false,
    open var updated: Int = 0,
    open var created: Int = 0
): RealmObject()

open class Purpose(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var serialNum: Int = 0,
    open var isExist: Boolean = false,
    open var updated: Int = 0,
    open var created: Int = 0
): RealmObject()

