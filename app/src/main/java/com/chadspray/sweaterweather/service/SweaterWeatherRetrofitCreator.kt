package com.chadspray.sweaterweather.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SweaterWeatherRetrofitCreator {

    companion object {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://j9l4zglte4.execute-api.us-east-1.amazonaws.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var weatherService: WeatherService = retrofit.create(WeatherService::class.java)
    }
}