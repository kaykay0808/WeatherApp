package com.example.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.model.local.Favorite
import com.example.weatherapp.data.model.local.TemperatureUnitSetting

@Database(
    entities = [Favorite::class, TemperatureUnitSetting::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}