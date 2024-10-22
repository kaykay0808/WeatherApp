package com.example.weatherapp.ui.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: WeatherRepository) : ViewModel() {

    val data: MutableState<DataOrException<Weather, Boolean, Exception>> = mutableStateOf(
    DataOrException(null, true, Exception(""))
    )

    init {
    loadWeather()
    }

    private fun loadWeather() {
    getWeather("Seattle")
    }

    private fun getWeather(city: String) {
    viewModelScope.launch {
    if (city.isEmpty()) return@launch
    data.value.loading = true
    data.value = repository.getWeather(cityQuery = city)
    if (data.value.data.toString().isNotEmpty()) data.value.loading = false
    }
    Log.d("GET", "getWeather: ${data.value.data.toString()}")
    }
}
