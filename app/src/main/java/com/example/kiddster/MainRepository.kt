package com.example.kiddster

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kiddster.Network.Joke
import com.example.kiddster.Network.KiddsterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private val _responseStart = MutableLiveData<List<Joke>>()


    val responseStart: LiveData<List<Joke>>
        get() = _responseStart

    val allJokes : Array<Joke> by lazy {responseStart.value!!.toTypedArray()}


    fun getJokes() {


        KiddsterApi.retrofitService.getJokes().enqueue(
            object: Callback<List<Joke>>{
                override fun onResponse(call: Call<List<Joke>>,
                                        response: Response<List<Joke>>) {
                    _responseStart.value = response.body()
//                    _response.value = "${response.body()?.get(0)?.desc?.replace("\\n", (System.getProperty("line.separator") + "\n"))}"
//                    _response.value?.let { Log.d("mane", it) }
                }

                override fun onFailure(call: Call<List<Joke>>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                    _response.value?.let { Log.d("mane", it) }
                }
            })


    }


}