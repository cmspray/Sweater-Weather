package com.chadspray.sweaterweather.ui.home

import WeatherDTO
import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chadspray.sweaterweather.R
import com.chadspray.sweaterweather.service.SweaterResponse
import com.chadspray.sweaterweather.ui.weather.WeatherView
import com.google.android.gms.location.LocationServices

const val LOCATION_REQUEST_CODE = 5

class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var errorTextView: TextView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var weatherView: WeatherView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        errorTextView = root.findViewById(R.id.errorView)
        refreshLayout = root.findViewById(R.id.refreshLayout)
        weatherView = root.findViewById(R.id.weatherView)
        refreshLayout.setOnRefreshListener {
            handleMissingLocationPermission()
        }
        handleMissingLocationPermission()
        return root
    }

    private fun handleMissingLocationPermission() {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            errorTextView.visibility = View.GONE
            requestWeather()
        } else {
            errorTextView.visibility = View.VISIBLE
            errorTextView.setText(R.string.requires_location_permission)
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
        }
    }

    private fun requestWeather() {
        Log.d(TAG, "Requesting Weather")
        refreshLayout.isRefreshing = true
        homeViewModel.getLocalWeather(LocationServices.getFusedLocationProviderClient(context!!), Geocoder(context))
           .observe(this, showWeather())
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            errorTextView.visibility = View.GONE
        } else {
            handleMissingLocationPermission()
        }
    }
}