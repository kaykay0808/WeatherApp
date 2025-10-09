package com.example.weatherapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.data.model.weather.WeatherItem
import com.example.weatherapp.utils.formatDecimals

@Composable
fun HumidityWindPressureRow(
    weatherItem: WeatherItem,
    isImperial: Boolean
) {
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
                text = "${formatDecimals(weatherItem.speed)} " + if (isImperial) "mph" else "m/s", // mph or m/s
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
