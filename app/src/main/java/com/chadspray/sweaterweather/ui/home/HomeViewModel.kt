package com.chadspray.sweaterweather.ui.home

import WeatherDTO
import android.location.Geocoder
import androidx.lifecycle.*
import com.chadspray.sweaterweather.service.SweaterResponse
import com.chadspray.sweaterweather.ui.weather.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import java.lang.Exception

class HomeViewModel : WeatherViewModel() {

    private val _zipcode = MutableLiveData<String>()

    private fun getZipcode(fusedLocationClient: FusedLocationProviderClient, geocoder: Geocoder) : LiveData<String> {
        fusedLocationClient.lastLocation.addOnSuccessListener {location ->
            try {
                _zipcode.postValue(geocoder
                    .getFromLocation(location.latitude, location.longitude, 1)[0]
                    .postalCode)
            } catch (ignored: Exception) {

            }
        }
        return _zipcode
    }

    fun getLocalWeather(fusedLocationClient: FusedLocationProviderClient, geocoder: Geocoder): LiveData<SweaterResponse<WeatherDTO>> {
        return Transformations.switchMap(getZipcode(fusedLocationClient, geocoder), this::getWeatherData)
    }

}