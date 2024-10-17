package com.example.weatherapp.model.weather

data class WeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

/** {city:{..}, cod: "200", message: 16.02, cnt: 7, list: [] }*/
