package com.example.weatherapp.ui.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.widgets.WeatherTopAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) { value = mainViewModel.getWeatherData(city = "Sandnes") }.value

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

// We are going to pass the data itself and not the viewmodel.
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    modifier: Modifier// = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = " for testing testing testing :${data.city.name} ")
        Text(text = " ka faen faen faen lol lol${data.city.country} ")
        Text(text = " for testing testing testing :${data.city.timezone} ")
        Text(text = " for testing testing testing :${data.city.population} ")
        Text(text = data.city.country)
        Text(text = data.city.name)
        Text(text = data.city.name)
        Text(text = data.city.country)
        Text(text = data.city.name)
    }

}

@Preview
@Composable
fun MainScaffoldPreview() {
    // MainScaffold(weather = null, navController = null)
}

