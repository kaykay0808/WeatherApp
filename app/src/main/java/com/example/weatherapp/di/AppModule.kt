package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // provide dao
    @Singleton
    @Provides
    fun provideDao(weatherDatabase: WeatherDatabase) = weatherDatabase.weatherDao()

    // provide database
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database" // favorite_table?
    )
        .fallbackToDestructiveMigration()
        .build()

    // provide api
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
