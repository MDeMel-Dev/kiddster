package com.example.kiddster

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : ViewModel() {

    private val mainRepository = MainRepository()

    val jokesList = mainRepository.responseStart

    private val _mainJoke = MutableLiveData<String>()

    val mainJoke: LiveData<String>
        get() = _mainJoke

    /**
     *
     *
     */

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */


    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                mainRepository.getJokes()
                while(jokesList.value.isNullOrEmpty()){
                delay(5000)
                }
                displayJokeStart()

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(jokesList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
                Log.d("mane", "Jokes list not available")
            }
        }
    }

    private fun displayJokeStart()
    {
        _mainJoke.value = mainRepository.allJokes.random().desc.replace("\\n", (System.getProperty("line.separator") + "\n"))
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}