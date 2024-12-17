package com.example.weatherapp.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

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
            .size(45.dp),
        //colorFilter = ColorFilter.tint(iconColor)
    )
}
