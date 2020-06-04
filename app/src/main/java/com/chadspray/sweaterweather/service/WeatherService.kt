package com.chadspray.sweaterweather.service

import WeatherDTO
import com.chadspray.sweaterweather.dto.ZipcodeDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WeatherService {

    @POST("ctl/weather")
    fun getWeather(@Body request: ZipcodeDTO): Call<WeatherDTO>
}