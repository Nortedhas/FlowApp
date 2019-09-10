// MARK: Parser

package com.example.ageone.SCAG

import com.example.ageone.External.Extensions.Realm.add
import org.json.JSONObject
import timber.log.Timber

class Parser {
fun parseAnyObject(json: JSONObject, type: DataBase) {
	json.optJSONArray(type.name)?.let{array ->
		for (i in 0 until array.length()) {
			val json = array[i] as JSONObject
			val obj = when (type) {
				DataBase.Announce -> {
					json.parseAnnounce()
				}
				DataBase.Audio -> {
					json.parseAudio()
				}
				DataBase.Chackra -> {
					json.parseChackra()
				}
				DataBase.Document -> {
					json.parseDocument()
				}
				DataBase.Image -> {
					json.parseImage()
				}
				DataBase.Order -> {
					json.parseOrder()
				}
				DataBase.Product -> {
					json.parseProduct()
				}
				DataBase.ProductSet -> {
					json.parseProductSet()
				}
				DataBase.Purpose -> {
					json.parsePurpose()
				}
				DataBase.Rune -> {
					json.parseRune()
				}
				DataBase.User -> {
					json.parseUser()
				}
				}
			add(obj)
		}
	}
}

}

// MARK: Parse JSON to Realm

fun JSONObject.parseAnnounce(): Announce {
	val some = Announce()
	some.link = optString("link", "")
	some.created = optInt("created", 0)
	some.hashId = optString("hashId", "")
	some.txtInfo = optString("txtInfo", "")
	some.__type = optString("__type", "")
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseAudio(): Audio {
	val some = Audio()
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.sourceName = optString("sourceName", "")
	some.file = optString("file", "")
	some.isExist = optBoolean("isExist", false)
	some.created = optInt("created", 0)
	return some
}

fun JSONObject.parseChackra(): Chackra {
	val some = Chackra()
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.serialNum = optInt("serialNum", 0)
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.hashId = optString("hashId", "")
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.__type = optString("__type", "")
	some.updated = optInt("updated", 0)
	some.txttext = optString("txttext", "")
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.original = optString("original", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.preview = optString("preview", "")
	some.isExist = optBoolean("isExist", false)
	some.created = optInt("created", 0)
	return some
}

fun JSONObject.parseOrder(): Order {
	val some = Order()
	some.type = optString("type", "")
	optJSONObject("productSet")?.let { productSet ->
		some.productSet = productSet.parseProductSet()
	}
	some.orderNum = optString("orderNum", "")
	some.hashId = optString("hashId", "")
	some.isExist = optBoolean("isExist", false)
	some.__status = optString("__status", "")
	some.userHashId = optString("userHashId", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	optJSONObject("product")?.let { product ->
		some.product = product.parseProduct()
	}
	some.price = optInt("price", 0)
	some.avaliableTime = optInt("avaliableTime", 0)
	Timber.i("Order: $some")
	return some
}

fun JSONObject.parseProduct(): Product {
	val some = Product()
	some.isPopular = optBoolean("isPopular", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	optJSONObject("audio")?.let { audio ->
		some.audio = audio.parseAudio()
	}
	optJSONObject("purpose")?.let { purposes ->
 		for (i in 0 until purposes.length()) {
 			some.purpose.add(
				purposes.optJSONObject("$i")?.let { purpose ->
					purpose.parsePurpose()
				}
			)
		}
	}
	some.hashId = optString("hashId", "")
	some.__duration = optString("__duration", "")
	some.isExist = optBoolean("isExist", false)
	some.isFree = optBoolean("isFree", false)
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.isQuickStart = optBoolean("isQuickStart", false)
	some.txtInfo = optString("txtInfo", "")
	some.isIntro = optBoolean("isIntro", false)
	some.created = optInt("created", 0)
	return some
}

fun JSONObject.parseProductSet(): ProductSet {
	val some = ProductSet()
	some.hashId = optString("hashId", "")
	some.isFree = optBoolean("isFree", false)
	some.isExist = optBoolean("isExist", false)
	optJSONObject("rune")?.let { runes ->
 		for (i in 0 until runes.length()) {
 			some.rune.add(
				runes.optJSONObject("$i")?.let { rune ->
					rune.parseRune()
				}
			)
		}
	}
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	optJSONObject("intro")?.let { intro ->
		some.intro = intro.parseProduct()
	}
	some.txtInfo = optString("txtInfo", "")
	optJSONObject("tracks")?.let { trackss ->
 		for (i in 0 until trackss.length()) {
 			some.tracks.add(
				trackss.optJSONObject("$i")?.let { tracks ->
					tracks.parseProduct()
				}
			)
		}
	}
	some.updated = optInt("updated", 0)
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parsePurpose(): Purpose {
	val some = Purpose()
	some.isExist = optBoolean("isExist", false)
	some.serialNum = optInt("serialNum", 0)
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseRune(): Rune {
	val some = Rune()
	some.isExist = optBoolean("isExist", false)
	some.serialNum = optInt("serialNum", 0)
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	some.role = optString("role", "")
	some.vipAccessTo = optInt("vipAccessTo", 0)
	some.updated = optInt("updated", 0)
	some.fcmToken = optString("fcmToken", "")
	some.name = optString("name", "")
	some.hashId = optString("hashId", "")
	some.email = optString("email", "")
	some.isExist = optBoolean("isExist", false)
	some.deviceId = optString("deviceId", "")
	some.phone = optString("phone", "")
	some.created = optInt("created", 0)
	return some
}