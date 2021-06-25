package com.example.kiddster

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : ViewModel() {

    private val mainRepository = MainRepository()

    val jokes_list = mainRepository.response

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                mainRepository.getJokes()

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(jokes_list.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
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