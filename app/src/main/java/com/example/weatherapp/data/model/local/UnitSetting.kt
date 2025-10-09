package com.example.weatherapp.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_tbl")
data class UnitSetting(
    // @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String
)