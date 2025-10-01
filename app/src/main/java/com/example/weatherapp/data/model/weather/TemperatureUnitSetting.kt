package com.example.weatherapp.data.model.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "settings_tbl")
data class TemperatureUnitSetting(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String
)
