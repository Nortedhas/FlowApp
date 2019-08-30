package com.example.ageone.External.HTTP.API

import android.provider.Settings
import com.example.ageone.Application.currentActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import java.util.*

enum class Routes(val path: String) {
    handshake("/handshake"),
    api("/api");
}

val android_id = Settings.Secure.getString(currentActivity?.contentResolver, Settings.Secure.ANDROID_ID)
val uuid = UUID.randomUUID().toString()

@JsonClass(generateAdapter = true)
data class hr (
    @Json(name = "deviceId")
    val deviceId: String
)

fun handshake() {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter: JsonAdapter<hr> = moshi.adapter(hr::class.java)


    Fuel.post("http://109.196.164.182/handshake")
        .jsonBody(adapter.toJson(hr(deviceId = uuid)))
        .also { Timber.i("$it") }
        .response { result ->
            Timber.i("Result:\n${result.get()}")
        }

    /*val apiService = ApiService.create()
    val getDb = apiService.handshake(
        DbBody(
            uuid
        )
    )

    Timber.i("$uuid")

    getDb.enqueue(object : Callback<DbResponse> {
        override fun onFailure(call: Call<DbResponse>, t: Throwable) {
            Timber.i("handshake: Fail")
        }

        override fun onResponse(call: Call<DbResponse>, response: Response<DbResponse>) {
            if (response.isSuccessful) {
                Timber.i("handshake: ok")
                Timber.i("handshake: ${response.raw()} body: ${response.body()}")
            }
        }

    })*/
}