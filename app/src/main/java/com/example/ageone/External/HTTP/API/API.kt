package com.example.ageone.External.HTTP.API

import com.example.ageone.Application.api
import com.example.ageone.Application.utils
import com.example.ageone.SCAG.DataBase
import com.example.ageone.SCAG.Parser
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

    fun handshake(): Promise<Unit> {
        return Promise{ resolve, _ ->

            Fuel.post(Routes.handshake.path)
                .jsonBody(createBody(mapOf(
                    "deviceId" to uuid
                )).toString())
                .responseString { request, response, result ->
                    val jsonObject = JSONObject(result.get())
                    utils.variable.token = jsonObject.optString("Token")
                    Timber.i("${result.get()}")
                    Timber.i("Token: ${utils.variable.token}")
                    cashTime = Date().time.toInt()
                    resolve(Unit)
                }
        }

    }

    fun request(params: Map<String, Any>, completion: (JSONObject) -> (Unit)) {

        Fuel.post(Routes.api.path)
            .jsonBody(createBody(params).toString())
            .header(DataBase.headers)
            .responseString { request, response, result ->
                result.fold({result ->
                    val jsonObject = JSONObject(result)
                    Timber.i("Request:\n $request Response:\n $response Result:\n $result")

                    val error = jsonObject.optString("error", "")
                    if (error != "") {
                        Timber.e("$error")
                    } else {
                        completion.invoke(jsonObject)
                    }

                },{error ->
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
            api.request(mapOf("router" to "mainLoad", "cashTime" to 0)) {jsonObject ->
                Timber.i("Object: $jsonObject")

                for (type in DataBase.values()) {
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
