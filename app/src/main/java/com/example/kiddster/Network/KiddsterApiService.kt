package com.example.kiddster.Network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface KiddsterApiService {
    @GET("main")
    fun getJokes():
            Call<String>
}

object KiddsterApi {
    val retrofitService : KiddsterApiService by lazy {
        retrofit.create(KiddsterApiService::class.java) }
}