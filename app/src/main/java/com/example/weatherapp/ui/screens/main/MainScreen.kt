package com.example.weatherapp.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.component.CircleInfo
import com.example.weatherapp.component.HumidityWindPressureRow
import com.example.weatherapp.component.SunsetRow
import com.example.weatherapp.component.WeatherList
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.ui.widgets.WeatherTopAppBar
import com.example.weatherapp.utils.formatDate

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) { value = mainViewModel.getWeatherData(city = "Oslo") }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        // Text(text = "MainScreen ${weatherData.data!!.city.country}")
        // To get the whole payload
        // Text(text = "MainScreen ${weatherData.data}")
        MainScaffold(
            weather = weatherData.data!!,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()// pinnedScrollBehavior()  // exitUntilCollapsedScrollBehavior
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                title = weather.city.name + ", ${weather.city.country}",
                // icon = Icons.AutoMirrored.Filled.ArrowBack,
                navController = navController,
                scrollBehavior = scrollBehavior,
                elevation = 4.dp
            ) {
                Log.d("TAG", "MainScaffold: ButtonClicked")
            }
        },
        content = { paddingValues ->
            MainContent(
                data = weather,
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Composable
fun MainContent(
    data: Weather,
    modifier: Modifier = Modifier
) {
    val weatherItem = data.list[0]

    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Log.d("TheIconImage", "This Image = ${weatherItem.weather[0].icon}")


    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Date
        Text(
            modifier = Modifier
                .padding(6.dp),
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = "${data.city.country} ")
        CircleInfo(
            imageUrl = imageUrl,
            weatherItem = weatherItem,
            // data = data
        )
        HumidityWindPressureRow(weatherItem = weatherItem)
        HorizontalDivider()
        SunsetRow(weatherItem = weatherItem)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        WeatherList(items = data.list)
    }
}
