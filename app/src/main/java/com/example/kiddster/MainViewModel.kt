package com.example.kiddster

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kiddster.Network.Joke
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : ViewModel() {

    private val mainRepository = MainRepository()

    val jokesList = mainRepository.responseStart

    val allJokes : Array<Joke> by lazy {mainRepository.allJokes}

    /**
     *
     * mainJoke is a string
     */

    private val _mainJoke = MutableLiveData<String>()

    val mainJoke: LiveData<String>
        get() = _mainJoke

    /**
     *
     *
     */

    private var nextIndex = 0

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
                Log.d("mane", "Network request started")
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
        var temp = mainRepository.allJokes.random()
        _mainJoke.value = temp.desc.replace("\\n", (System.getProperty("line.separator") + "\n"))
        nextIndex = temp.id.toInt()
    }

    fun nextJoke()
    {
        if(nextIndex < mainRepository.allJokes.size) {
        var temp = mainRepository.allJokes.get(nextIndex)
        _mainJoke.value = temp.desc.replace("\\n", (System.getProperty("line.separator") + "\n"))
        nextIndex = temp.id.toInt()
    }
        else
    {
        nextIndex = 0
        var temp = mainRepository.allJokes.get(nextIndex)
        _mainJoke.value = temp.desc.replace("\\n", (System.getProperty("line.separator") + "\n"))
        nextIndex = temp.id.toInt()
    }
    }

    fun previousJoke()
    {
        if(nextIndex == 0) {
            nextIndex = mainRepository.allJokes.size - 1
            var temp = mainRepository.allJokes.get(nextIndex)
            _mainJoke.value = temp.desc.replace("\\n", (System.getProperty("line.separator") + "\n"))
            nextIndex = nextIndex - 1
        }
        else
        {
            var temp = mainRepository.allJokes.get(nextIndex)
            _mainJoke.value = temp.desc.replace("\\n", (System.getProperty("line.separator") + "\n"))
            nextIndex = nextIndex - 1
        }
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