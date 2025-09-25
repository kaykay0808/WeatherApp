package com.example.weatherapp.data.model.weather

// kotlin class which we can parse json response we get from our api
data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Double
)