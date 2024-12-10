package com.example.weatherapp.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.weather.WeatherItem

@Composable
fun WeatherList(
    modifier: Modifier = Modifier,
    items: List<WeatherItem>
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFFEEF1EF),
        shape = RoundedCornerShape(14.dp)
    ) {
        LazyColumn(
            modifier = modifier.padding(2.dp),
            contentPadding = PaddingValues(1.dp) // This padding is going to be applied to the items inside of our lazy column.
        ) {
            items(items = items) { item: WeatherItem ->
                // Text(item.temp.max.toString())
                // Item row. | "Day" | WeatherIcon | "Weather description" | Temperature
                WeatherDetailsRow(weather = item)
            }
        }
    }
}
