package com.chadspray.sweaterweather.ui.weather

import WeatherDTO
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.chadspray.sweaterweather.R
import com.nostra13.universalimageloader.core.ImageLoader


class WeatherView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    FrameLayout(context, attrs, defStyle) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val currentTempView: TextView
    private val highLowView: TextView
    private val cityTextView: TextView
    private val stateTextView: TextView
    private val currentDescription: TextView
    private val sweaterView: TextView
    private val weatherIcon: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.weather_view, this, true)
        currentTempView = findViewById(R.id.currentTempView)
        highLowView = findViewById(R.id.highLowView)
        cityTextView = findViewById(R.id.cityTextView)
        stateTextView = findViewById(R.id.stateTextView)
        currentDescription = findViewById(R.id.currentDescription)
        sweaterView = findViewById(R.id.sweaterTextView)
        weatherIcon = findViewById(R.id.weatherIcon)
    }

    fun init(weather: WeatherDTO) {
        currentTempView.text =
            context.getString(R.string.currentTemp, weather.today.temperature.toString())
        highLowView.text = context.getString(
            R.string.highLowText,
            weather.today.lowTemperature.toString(),
            weather.today.highTemperature.toString()
        )
        cityTextView.text = weather.today.city
        stateTextView.text = weather.today.state
        currentDescription.text = weather.today.description

        sweaterView.text = if (weather.today.shouldWearSweater)
            context.getString(R.string.require_sweater_text)
        else
            context.getString(R.string.do_not_need_sweater_text)

        val imageLoader: ImageLoader = ImageLoader.getInstance()
        imageLoader.displayImage(weather.today.iconLink, weatherIcon);
    }
}