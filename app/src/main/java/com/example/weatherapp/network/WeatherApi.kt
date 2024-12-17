package com.example.weatherapp.network

import com.example.weatherapp.data.model.weather.Weather
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
        @Query("q") query: String, // query is the city we are searching
        @Query("units") units: String = "metric", // imperial
        @Query("appid") appid: String = API_KEY
    ): Weather
}

// https://api.openweathermap.org/data/2.5/forecast/daily?q=lisbon&appid=ed60fcfbd110ee65c7150605ea8aceea&units=imperial
