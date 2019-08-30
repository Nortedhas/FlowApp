package com.example.ageone.External.HTTP.API

import okhttp3.Interceptor
import okhttp3.OkHttpClient

/*interface ApiService {

    @POST("handshake")
    fun handshake(@hr body: DbBody): Call<DbResponse>

    *//**
     * Companion object to create the ApiService
     *//*
    companion object Factory {

        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("http://109.196.164.182/")
                .client(client.build())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}*/



/*object Apifactory{

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
//            .header("x-access-token", token)
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    //OkhttpClient for building http request url
    val logging =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient = OkHttpClient.Builder()

    init {
        httpClient.addInterceptor(logging)  // <-- this is the important line!
    }

    fun retrofit() = Retrofit.Builder()
        .baseUrl("http://109.196.164.182")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(httpClient.build())
        .build()

    val curApi: CurApi
        get() = retrofit().create(CurApi::class.java)

}


interface CurApi {

    @GET("/handshake/")
    fun handshake(@hr deviceId: DbBody): Call<DbResponse>
}*/

data class DbResponse(
    val response: String
)

data class DbBody(
    val error: String
)