package com.example.ageone.External.HTTP.API

import android.provider.Settings
import com.example.ageone.Application.api
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.Models.User.user
import com.example.ageone.SCAG.Announce
import com.example.ageone.SCAG.DataBase
import com.example.ageone.SCAG.Model
import com.example.ageone.SCAG.Product
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.swarmnyc.promisekt.Promise
import com.swarmnyc.promisekt.Promise.Companion.resolve
import io.realm.Realm

import net.alexandroid.shpref.ShPref
import org.json.JSONObject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class API {
    var cashTime: Int
        get() = ShPref.getInt("ApiCashTime", 0)
        set(value) = ShPref.put("ApiCashTime", value)

    fun handshake(): Promise<Unit> {
        return Promise{ resolve, _ ->
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            Timber.i("iii: handshake $currentDate")

            val adapter: JsonAdapter<body> = moshi.adapter(body::class.java)

            FuelManager.instance.basePath = "http://45.141.102.83"

            Fuel.post("/handshake")
                .jsonBody(adapter.toJson(body(deviceId = uuid)))
                .responseString { request, response, result ->
                    val jsonObject: JSONObject = JSONObject(result.get())
                    utils.variable.token = jsonObject.optString("Token")
                    Timber.i("${result.get()}")
                    Timber.i("Token: ${utils.variable.token}")
                    cashTime = Date().time.toInt()
                    resolve(Unit)
                }
        }

    }

    fun request(parametr: Map<String, Any>, completion: (JSONObject) -> (Unit)) {
        val body = createBody(mapOf("router" to "mainLoad", "cashTime" to 0))

        Fuel.post("/api")
            .jsonBody(body.toString())
            .header(DataBase.headers)
            .responseString { request, response, result ->
                result.fold({result ->
                    val jsonObject: JSONObject = JSONObject(result)
                    Timber.i("Request:\n $request Response:\n $response Result:\n $result")

                    val error = jsonObject.optString("error", "")
                    if (error != "") {
                        Timber.e("$error")
                    } else {
                        completion.invoke(jsonObject)
                    }

                    //TODO: распарсить error - вевсти elt completion.invoke()
                },{error ->
                    Timber.e("${error.response.responseMessage}")
                })

            }
    }

    fun createBody(parametr: Map<String, Any>): JSONObject {
        val body = JSONObject()
        body.put("router", "mainLoad")
        body.put("cashTime", 0)


        return body
    }


//    fun handleUser()

    fun requestMainLoad(): Promise<Unit> {
        return Promise { resolve, _ ->
            api.request(mapOf("router" to "mainLoad", "cashTime" to 0)) {jsonObject ->
                //TODO: parsing
                Timber.i("Hello parsing")
                Timber.i("Object: $jsonObject")

                for (type in DataBase.values()) {
                    jsonObject.optJSONArray(type.name)?.let{array ->
                        for (i in 0 until array.length()) {
                            val json = array[i] as JSONObject
                            val model = Model()
                            model.add(model.create(json, type))
                        }
                    }
                }
                Timber.i("Announces: ${Realm.getDefaultInstance().where(Announce::class.java).findAll()}")
                Timber.i("Products: ${Realm.getDefaultInstance().where(Product::class.java).findAll()}")

            }
        }
    }
}


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




/*open class Dog(
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
    *//*val user = User("aaa", true, 1)
    user.strings.add("meeeow")*//*
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



}*/


