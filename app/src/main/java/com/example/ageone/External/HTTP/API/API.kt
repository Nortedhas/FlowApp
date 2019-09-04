package com.example.ageone.External.HTTP.API

import com.example.ageone.Application.api
import com.example.ageone.Application.utils
import com.example.ageone.SCAG.Parser
import com.example.ageone.SCAG.RealmObjects
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.swarmnyc.promisekt.Promise
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
            .header(RealmObjects.DataBase.headers)
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
                Timber.i("Object: $jsonObject")

                for (type in RealmObjects.DataBase.values()) {
                    Parser().parseAnyObject(jsonObject, type)
                }

            }
        }
    }
}


enum class Routes(val path: String) {
    handshake("/handshake"),
    api("/api");
}

val uuid = UUID.randomUUID().toString()

@JsonClass(generateAdapter = true)
data class body (
    @Json(name = "deviceId")
    val deviceId: String
)