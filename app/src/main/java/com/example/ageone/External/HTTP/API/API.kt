package com.example.ageone.External.HTTP.API

import com.example.ageone.Models.User.user
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import timber.log.Timber

open class Dog(
    @PrimaryKey open var primaryKey: String = "",
    open var name: String = "",
    open var age: Int = 0
): RealmObject()

open class User(
    @PrimaryKey open var primaryKey: String = "",
    open var isExist: Boolean = false,
    open var created: Int = 0,
    open var strings: RealmList<String> = RealmList(),
    open var favorite: Dog? = null,
    open var dogs: RealmList<Dog> = RealmList()
): RealmObject()

fun addUser(realm: Realm, user: User): Boolean {
    try {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(user)
        realm.commitTransaction()
        return true
    } catch (e: Exception) {
        println(e)
        return false
    }
}

fun handshake(){
    /*val user = User("aaa", true, 1)
    user.strings.add("meeeow")*/
    val user2 = User("aab", true, 2)
    user2.favorite = Dog("mimi", "catti", 2)
    user2.dogs.add(Dog("123","cat", 3))
    val realm = Realm.getDefaultInstance()

    val dog = Dog("mimi", "catti", 2)
    Timber.i("i1 Before: ${user.data.fav}")

    user.data.fav = dog

    Timber.i("i1 After: ${user.data.fav}")


//    addUser(realm, user)
    addUser(realm, user2)

    val users = realm.where(User::class.java).findAll()
    Timber.i("Users: $users")

}

/*
enum class Routes(val path: String) {
    handshake("/handshake"),
    api("/api");
}

val android_id = Settings.Secure.getString(currentActivity?.contentResolver, Settings.Secure.ANDROID_ID)
val uuid = UUID.randomUUID().toString()

@JsonClass(generateAdapter = true)
data class body (
    @Json(name = "deviceId")
    val deviceId: String
)

fun handshake() {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val adapter: JsonAdapter<body> = moshi.adapter(body::class.java)

    FuelManager.instance.basePath = "http://109.196.164.182"

    Fuel.post("/handshake")
        .jsonBody(adapter.toJson(body(deviceId = uuid)))
        .also { Timber.i("$it") }
        .responseString { request, response, result ->
            val jsonObject: JSONObject = JSONObject(result.get())
            utils.variable.token = jsonObject.optString("Token")

            jsonObject.optJSONObject("User")?.let { jsonObject ->
                user.data.phone = jsonObject.optString("phone")
            }
        }

}*/
