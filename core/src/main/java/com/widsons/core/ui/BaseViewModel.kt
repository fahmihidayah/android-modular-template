package com.widsons.core.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created on : October/22/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
open class BaseViewModel : ViewModel() {
    open var showToastError : Boolean = false

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _messageLiveData = MutableLiveData<Any>()
    val messageLiveData : LiveData<Any> = _messageLiveData

    protected val _errorMessage = MutableLiveData<Any>()
    val errorMessage: LiveData<Any> = _errorMessage

    protected val _errorPageLiveData = MutableLiveData<Boolean>()
    var errorPageLiveData : LiveData<Boolean> = _errorPageLiveData

    protected val _finishLiveData = MutableLiveData<Boolean>()
    val finishLiveData : LiveData<Boolean> = _finishLiveData



    fun launchRequest(
        showLoading: Boolean = true,
        onError : (Exception) -> Unit = {},
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                _errorPageLiveData.value = false
                _isLoading.value = showLoading
                block()
            } catch (exception: Exception) {
                Log.e("request", "hide content on failure", exception)
                if(showToastError)
                    _messageLiveData.value = exception.message
                onError(exception)
                _errorPageLiveData.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}