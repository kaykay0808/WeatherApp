package com.example.weatherapp.ui.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.model.weather.WeatherItem
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDecimals
import com.example.weatherapp.widgets.WeatherTopAppBar

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
        // Todo: Adding humidity wind and pressure row
        HumidityWindPressureRow(weatherItem = weatherItem)
        HorizontalDivider()
    }
}

@Composable
private fun CircleInfo(
    imageUrl: String,
    weatherItem: WeatherItem,
    // data: Weather
) {
    Surface( // Circle shape
        modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
        shape = CircleShape,
        color = Color(0xFFFFC400)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            // Image =
        ) {
            WeatherStateImage(imageUrl = imageUrl)
            // Temperature
            Text(
                text = formatDecimals(weatherItem.temp.day) + "°",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = weatherItem.weather[0].main,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weatherItem: WeatherItem) {
    Row(
        modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Another row for the icon and number (Each row represent one info inside the row)
        // (Item 1)
        Row(
            modifier = Modifier
            .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem.humidity}%",
                style = MaterialTheme.typography.bodySmall
            )
        }
        // (Item 2)
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem.pressure} psi",
                style = MaterialTheme.typography.bodySmall
            )
        }
        // (item 3)
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem.humidity} mph",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun WeatherStateImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    //iconColor: Color = Color.Black
) {
    // image for Coil
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "icon",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp),
        //colorFilter = ColorFilter.tint(iconColor)
    )
}

@Preview
@Composable
fun MainScaffoldPreview() {
    // MainScaffold(weather = null, navController = null)
}

