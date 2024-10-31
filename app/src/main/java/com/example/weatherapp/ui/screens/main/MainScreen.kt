package com.example.weatherapp.ui.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
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
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Log.d("TheIconImage", "This Image = ${data.list[0].weather[0].icon}")


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
            text = formatDate(data.list[0].dt),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = "${data.city.country} ")
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
                    text = formatDecimals(data.list[0].temp.day) + "°",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = data.list[0].weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }
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

@Composable
fun IconTesting() {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(
            width = 2.dp,
            color = Color.LightGray
        )

    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(95.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun icon"
            )
            Text(
                text = "Hello Sun!",
                style = MaterialTheme.typography.titleLarge, // h5
                color = Color.LightGray
            )
        }
    }
}

/*@Preview
@Composable
fun MainScaffoldPreview() {
    // MainScaffold(weather = null, navController = null)
}*/

