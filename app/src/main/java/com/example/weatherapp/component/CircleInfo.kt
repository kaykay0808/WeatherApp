package com.example.weatherapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.weather.WeatherItem
import com.example.weatherapp.utils.formatDecimals

@Composable
fun CircleInfo(
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

@Preview
@Composable
fun CircleInfoPreview() {
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
        ) {
            Image(
                modifier = Modifier
                    .size(95.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun icon"
            )
            Text(
                text = "56°",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Rainy",
                fontStyle = FontStyle.Italic
            )
        }
    }
}

