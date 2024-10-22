package com.example.weatherapp.network

import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

// Creating different object with different instances of itself
@Singleton
interface WeatherApi {
    // retrofit: fetch data
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric", // imperial
        @Query("appid") appid: String = API_KEY
    ): Weather
}