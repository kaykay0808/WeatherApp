package com.example.weatherapp.ui.screens.main

import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.data.model.weather.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String) : DataOrException<Weather,Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }
}
