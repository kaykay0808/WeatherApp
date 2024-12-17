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
import com.example.weatherapp.utils.formatDateTime

@Composable
fun SunsetRow(weatherItem: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Sunrise
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(weatherItem.sunrise),
                style = MaterialTheme.typography.bodySmall
            )
        }
        // Sunset
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                text = formatDateTime(weatherItem.sunset),
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunrise Icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
