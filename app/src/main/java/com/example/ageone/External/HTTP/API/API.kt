package com.example.ageone.External.HTTP.API

import com.example.ageone.Application.api
import com.example.ageone.Application.utils
import com.example.ageone.Models.User.user
import com.example.ageone.SCAG.DataBase
import com.example.ageone.SCAG.Parser
import com.example.ageone.SCAG.userData
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.swarmnyc.promisekt.Promise
import net.alexandroid.shpref.ShPref
import org.json.JSONObject
import timber.log.Timber
import java.util.*

class API {
    var cashTime: Int
        get() = ShPref.getInt("ApiCashTime", 0)
        set(value) = ShPref.put("ApiCashTime", value)

    val parser = Parser()

    fun handshake(): Promise<Unit> {
        return Promise{ resolve, _ ->

            Fuel.post(Routes.Handshake.path)
                .jsonBody(createBody(mapOf(
                    "deviceId" to uuid
                )).toString())
                .responseString { request, response, result ->
                    val jsonObject = JSONObject(result.get())
                    utils.variable.token = jsonObject.optString("Token")
                    cashTime = Date().time.toInt()
                    parser.userData(jsonObject)

                    Timber.i("${result.get()}")
                    Timber.i("Token: ${utils.variable.token}")
                    resolve
                }
        }

    }

    fun request(params: Map<String, Any>, completion: (JSONObject) -> (Unit)) {

        Fuel.post(Routes.Api.path)
            .jsonBody(createBody(params).toString())
            .header(DataBase.headers)
            .responseString { request, response, result ->
                result.fold({ result ->
                    val jsonObject = JSONObject(result)
                    Timber.i("Request:\n $request Response:\n $response Result:\n $result")

                    val error = jsonObject.optString("error", "")
                    if (error != "") {
                        Timber.e("$error")
                    } else {
                        completion.invoke(jsonObject)
                    }

                },{ error ->
                    Timber.e("${error.response.responseMessage}")
                })

            }
    }

    fun createBody(params: Map<String, Any>): JSONObject {
        val body = JSONObject()
        params.forEach { (key, value) ->
            body.put(key, value)
        }

        return body
    }


//    fun handleUser()

    fun requestMainLoad(): Promise<Unit> {
        return Promise { resolve, _ ->
            //TODO change cashtime
            api.request(mapOf("router" to "mainLoad", "cashTime" to 0)) { jsonObject ->
                Timber.i("Object: $jsonObject")

                for (type in DataBase.values()) {
                    parser.parseAnyObject(jsonObject, type)
                }
                Timber.i("Parsing end")
                resolve
            }

        }
    }
}


enum class Routes(val path: String) {
    Handshake("/handshake"),
    Database("/database"),
    Api("/api");
}

val uuid = UUID.randomUUID().toString()
