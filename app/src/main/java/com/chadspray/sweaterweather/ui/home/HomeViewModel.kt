package com.chadspray.sweaterweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _hasLocationPermission = MutableLiveData<Boolean>().apply {
        value = false
    }

    val hasLocationPermission: LiveData<Boolean> = _hasLocationPermission

    fun setLocationPermissionGranted() = _hasLocationPermission.postValue(true)

}