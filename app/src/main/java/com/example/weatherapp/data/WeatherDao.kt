package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.data.model.local.Favorite
import com.example.weatherapp.data.model.weather.TemperatureUnitSetting
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    // Creating an item or updating an item (CRUD)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(weatherDataFavorite: Favorite)

    @Query("SELECT * FROM favorite_table")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_table where city =:city")
    suspend fun getFavById(city: String): Favorite

    @Update
    suspend fun updateFavorite(weatherDataFavorite: Favorite)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(weatherDataFavorite: Favorite)

    // Unit table
    @Query("SELECT * FROM settings_tbl")
    fun getUnits(): Flow<List<TemperatureUnitSetting>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: TemperatureUnitSetting)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: TemperatureUnitSetting)

    @Query("DELETE FROM settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: TemperatureUnitSetting)

}
