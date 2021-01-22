package com.example.openweather.ui.weather.view.fragment

import android.util.Log
import com.example.domain.model.WeatherInfor
import com.example.openweather.R
import com.example.openweather.common.extension.hideKeyboard
import com.example.openweather.common.extension.subcribe
import com.example.openweather.ui.base.*
import com.example.openweather.ui.weather.presentation.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {
    private val viewModel: WeatherViewModel by viewModel()
    override fun getLayout(): Int {
        return R.layout.fragment_weather
    }

    override fun onViewReady() {
        subcribeToData()
        getWeatherButton.setOnClickListener {
            weatherFragmentContainer.hideKeyboard()
            viewModel.getWeatherForLocation(cityEditText.text.toString())
        }
        getWeatherDetailsButton.setOnClickListener {
            navigator.showWeatherDetails()
        }
    }

    private fun subcribeToData() {
        viewModel.viewState.subcribe(this, ::handleViewState)
    }

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private fun handleViewState(viewState: ViewState<WeatherInfor>) {
        when (viewState) {
            is Success -> handleSuccess(viewState.data)
            is Error -> handleError(viewState.error.localizedMessage)
            is Loading -> showLoading(weatherProgressBar)
            is NoInternet -> handleNoInternet()
        }
    }

    private fun handleSuccess(weatherInfor: WeatherInfor) {
        hideLoading(weatherProgressBar)
        temperatureTextView.text =
            "temperature: " + weatherInfor.convertKelvinToCelsius().toString()
        pressureTextView.text = "pressure: " + weatherInfor.pressure.toString()
        humidityTextView.text = "humidity: " + weatherInfor.humidity.toString()
    }

    private fun handleError(error: String) {
        hideLoading(weatherProgressBar)
        Log.d("error", error)
        showError(error, weatherFragmentContainer)
    }

    private fun handleNoInternet() {
        hideLoading(weatherProgressBar)
        showError("No Internet connection", weatherFragmentContainer)
    }

}