package com.chadspray.sweaterweather.ui.weather

import WeatherDTO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chadspray.sweaterweather.dto.ZipcodeDTO
import com.chadspray.sweaterweather.service.SweaterResponse
import com.chadspray.sweaterweather.service.SweaterWeatherRetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class WeatherViewModel : ViewModel() {

    private val liveWeatherData = MutableLiveData<SweaterResponse<WeatherDTO>>()

    fun getWeatherData(zip: String?): LiveData<SweaterResponse<WeatherDTO>> {
        zip?.let {
            SweaterWeatherRetrofitCreator.weatherService.getWeather(ZipcodeDTO(zip)).enqueue(object : Callback<WeatherDTO> {
                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                    liveWeatherData.postValue(SweaterResponse(false))                }

                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    liveWeatherData.postValue(SweaterResponse(response.isSuccessful, response.body()))
                }
            })
        } ?: liveWeatherData.postValue(SweaterResponse(false))
        return liveWeatherData
    }
}