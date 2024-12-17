package com.example.weatherapp.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

// Annotation to compile to an entity
@Entity(tableName = "favorite_table")
data class Favorite(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "country")
    val country: String,
)
