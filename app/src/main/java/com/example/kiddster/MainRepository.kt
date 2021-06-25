package com.example.kiddster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kiddster.Network.KiddsterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private val _response = MutableLiveData<String>()


    val response: LiveData<String>
        get() = _response


    fun getJokes() {
        _response.value = "Jokes list!"

        KiddsterApi.retrofitService.getJokes().enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            })
    }


}