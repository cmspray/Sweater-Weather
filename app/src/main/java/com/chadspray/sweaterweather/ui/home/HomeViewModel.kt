package com.chadspray.sweaterweather.ui.home

import WeatherDTO
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.lifecycle.*
import com.chadspray.sweaterweather.service.SweaterResponse
import com.chadspray.sweaterweather.ui.weather.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import java.lang.Exception

class HomeViewModel : WeatherViewModel() {

    private val _zipcode = MutableLiveData<String>()
    lateinit var geocoder: Geocoder

    private val locationCallback = object: LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationUpdated(locationResult?.lastLocation)
        }
    }

    private fun getZipcode(fusedLocationClient: FusedLocationProviderClient) : LiveData<String> {
        fusedLocationClient.requestLocationUpdates(LocationRequest(), locationCallback, Looper.getMainLooper())
        fusedLocationClient.lastLocation.addOnSuccessListener {
            locationUpdated(it)
        }
        return _zipcode
    }

    private fun locationUpdated(location: Location?) {
        try {
            _zipcode.postValue(geocoder
                .getFromLocation(location!!.latitude, location!!.longitude, 1)[0]
                .postalCode)
        } catch (ignored: Exception) { // catches NPE's
            _zipcode.postValue(null)
        }
    }

    fun getLocalWeather(fusedLocationClient: FusedLocationProviderClient): LiveData<SweaterResponse<WeatherDTO>> {
        return Transformations.switchMap(getZipcode(fusedLocationClient), this::refreshData)
    }

}