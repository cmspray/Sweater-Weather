package com.chadspray.sweaterweather.service

data class SweaterResponse<T>(val success: Boolean, val responseBody: T? = null)