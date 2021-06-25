package com.example.kiddster.Network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "http://kiddster-api.herokuapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface KiddsterApiService {
    @GET("main")
    fun getJokes():
            Call<List<Joke>>
}

object KiddsterApi {
    val retrofitService : KiddsterApiService by lazy {
        retrofit.create(KiddsterApiService::class.java) }
}