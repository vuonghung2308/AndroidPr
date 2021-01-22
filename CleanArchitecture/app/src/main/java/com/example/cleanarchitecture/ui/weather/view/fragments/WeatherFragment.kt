package com.example.cleanarchitecture.ui.weather.view.fragments

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.common.convertKelvinToCelsius
import com.example.cleanarchitecture.common.extensions.hideKeyboard
import com.example.cleanarchitecture.common.extensions.onClick
import com.example.cleanarchitecture.common.extensions.snackbar
import com.example.cleanarchitecture.common.extensions.subcribe
import com.example.cleanarchitecture.ui.base.*
import com.example.cleanarchitecture.ui.weather.presentation.WeatherViewModel
import com.example.domain.model.WeatherInfor
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {

    private val viewModel: WeatherViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_weather

    override fun viewReady() {
        subcribeToData()
        getWeather.onClick {
            weatherFragmentContainer.hideKeyboard()
            viewModel.getWeatherForLocation(cityInput.text.toString())
        }
        showWeatherDetails.onClick {
            appFragmentNavigator.showWeatherDetail()
        }
    }

    private fun subcribeToData() {
        viewModel.viewState.subcribe(this, ::handleViewState)
    }

    fun handleViewState(viewState: ViewState<WeatherInfor>) {
        when (viewState) {
            is Loading -> showLoading(weatherLoadingProgress)
            is Success -> showWeatherData(viewState.data)
            is Error -> handleError(viewState.error.localizedMessage)
            is NoInternetState -> showNoInternetError()
        }
    }

    private fun handleError(error: String) {
        hideLoading(weatherLoadingProgress)
        showError(error, weatherFragmentContainer)
    }

    private fun showNoInternetError() {
        hideLoading(weatherLoadingProgress)
        snackbar("No Internet connection available. Please try again", weatherFragmentContainer)
    }

    fun showWeatherData(weartherInfor: WeatherInfor) {
        hideLoading(weatherLoadingProgress)
        temperature.text = convertKelvinToCelsius(weartherInfor.temperature)
        pressure.text = weartherInfor.pressure.toString()
        humidity.text = weartherInfor.humidity.toString()
    }

    companion object {
        fun newInstance() = WeatherFragment()
    }
}