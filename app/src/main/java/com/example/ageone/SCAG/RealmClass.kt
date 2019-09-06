package com.example.ageone.SCAG
// MARK: Realm Class

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Announce (
	open var created: Int = 0,
	open var txtInfo: String = "",
	open var link: String = "",
	open var updated: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var __type: String = "",
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Audio (
	open var file: String = "",
	open var sourceName: String = "",
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var updated: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Chackra (
	open var serialNum: Int = 0,
	open var name: String = "",
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var created: Int = 0,
	open var updated: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var __type: String = "",
	open var txttext: String = "",
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var preview: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var original: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Order (
	open var userHashId: String = "",
	open var product: Product? = null,
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var updated: Int = 0,
	open var avaliableTime: Int = 0,
	open var productSet: ProductSet? = null,
	open var type: String = "",
	open var price: Int = 0,
	open var __status: String = "",
	open var orderNum: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Product (
	open var isFree: Boolean = false,
	open var audio: Audio? = null,
	open var __duration: String = "",
	open var isIntro: Boolean = false,
	open var txtInfo: String = "",
	open var created: Int = 0,
	open var isExist: Boolean = false,
	open var isQuickStart: Boolean = false,
	open var name: String = "",
	open var updated: Int = 0,
	open var purpose: RealmList<Purpose> = RealmList(),
	open var isPopular: Boolean = false,
	open var image: Image? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class ProductSet (
	open var created: Int = 0,
	open var txtInfo: String = "",
	open var updated: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var tracks: RealmList<Product> = RealmList(),
	open var isFree: Boolean = false,
	open var intro: Product? = null,
	open var rune: RealmList<Rune> = RealmList(),
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Purpose (
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var serialNum: Int = 0,
	open var created: Int = 0,
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Rune (
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var serialNum: Int = 0,
	open var created: Int = 0,
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var role: String = "",
	open var created: Int = 0,
	open var updated: Int = 0,
	open var email: String = "",
	open var fcmToken: String = "",
	open var deviceId: String = "",
	open var phone: String = "",
	open var vipAccessTo: Int = 0,
	open var isExist: Boolean = false,
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()