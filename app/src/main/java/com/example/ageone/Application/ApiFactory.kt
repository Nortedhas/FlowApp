package com.example.ageone.Application

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object Apifactory{

    val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjliYzllNTZhLWQzZmUtNGYwYS05M2IyLWY2" +
            "MzgzOGJlZTAzMyIsImRldmljZUlkIjoiZGV2aWNlSWQiLCJpc0FkbWluIjp0cnVlLCJpYXQiOjE1NjUzNTAwMTEsImV4cCI" +
            "6MTU2NTM3MTYxMX0.HZ4EdfaqQmKbUGHzOhs1c1v2ktqJ81B21uvcFqhEm4WeT5TzSVNpCfDRSWI6lOiqTbzioFDw5UDtZWJt1ZppH" +
            "akNFGwIkZjh7z0u8PslxXAYdXTVjDUmWkewHGV4f_jsMV6ed4_7LzJEpJzOVRn0bQPcAoYURgbMJCfOTG1ubz5Ka7vnDIJS-0mVsuh1ef" +
            "VT5qaLYo7ZkDZV9ZqvgYfJnaum6JQmmjdjHRzyaZggqGczOMoLg5w-EvSvroH_DweIUBIaIa4M3FtNNLPPkgDxs_Jd1Opk7leI10-y0wZ9" +
            "zs3AcvlMQPRW53dqCwy3UfUDJ5VDAYARIdq60lAg18jbww"

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .header("x-access-token", token)
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    //OkhttpClient for building http request url
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()



    fun retrofit() = Retrofit.Builder()
        .client(client)
        .baseUrl("http://194.87.102.35/")
        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val curApi: CurApi
        get() = retrofit().create(CurApi::class.java)

}

interface CurApi {
    @POST("/database/")
    fun getDb(@Body dbBody: DbBody): Call<DbResponse>
}

data class DbResponse(
    val response: String
)

data class DbBody(
    val router: String,
    val collectionName: String,
    val elementId: String
)