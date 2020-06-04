package com.chadspray.sweaterweather.ui.dashboard

import WeatherDTO
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chadspray.sweaterweather.R
import com.chadspray.sweaterweather.service.SweaterResponse
import com.chadspray.sweaterweather.ui.home.HomeFragment
import com.chadspray.sweaterweather.ui.home.HomeViewModel
import com.chadspray.sweaterweather.ui.weather.WeatherView
import com.google.android.gms.location.LocationServices

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var errorTextView: TextView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var weatherView: WeatherView
    private lateinit var zipCodeEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        errorTextView = root.findViewById(R.id.errorView)
        errorTextView.setText(R.string.enter_zip_code_to_get_weather)
        refreshLayout = root.findViewById(R.id.refreshLayout)
        weatherView = root.findViewById(R.id.weatherView)
        zipCodeEdit = root.findViewById(R.id.zipCodeEdit)
        refreshLayout.setOnRefreshListener {
            updateWeatherView()
        }
        updateWeatherView()
        dashboardViewModel.getLiveWeatherData().observe(this, showWeather())
        zipCodeEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateWeatherView()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        return root
    }

    private fun updateWeatherView() {
        refreshLayout.isRefreshing = true
        val zip = zipCodeEdit.text.toString()
        if (zip.length != 5) {
            showZipError()
        } else {
            dashboardViewModel.refreshData(zip)
        }
    }

    private fun showWeather() = Observer<SweaterResponse<WeatherDTO>> { weatherResponse ->
        refreshLayout.isRefreshing = false
        if (weatherResponse.success && null != weatherResponse.responseBody) {
            errorTextView.visibility = View.GONE
            weatherView.visibility = View.VISIBLE
            weatherView.init(weatherResponse.responseBody)
        } else {
            errorTextView.visibility = View.VISIBLE
            weatherView.visibility = View.GONE
            errorTextView.setText(R.string.could_not_find_weather)
        }
    }

    private fun showZipError() {
        refreshLayout.isRefreshing = false
        errorTextView.visibility = View.VISIBLE
        weatherView.visibility = View.GONE
        errorTextView.setText(R.string.enter_zip_code_to_get_weather)
    }
}