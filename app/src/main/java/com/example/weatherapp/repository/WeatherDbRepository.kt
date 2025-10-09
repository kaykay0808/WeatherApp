package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.data.model.local.Favorite
import com.example.weatherapp.data.model.local.UnitSetting
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    // Mirroring Dao
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun getFavoriteById(city: String): Favorite = weatherDao.getFavById(city)
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    // Units
    fun getUnits(): Flow<List<UnitSetting>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: UnitSetting) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: UnitSetting) = weatherDao.updateUnit(unit)
    suspend fun deleteUnit(unit: UnitSetting) = weatherDao.deleteUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()

}