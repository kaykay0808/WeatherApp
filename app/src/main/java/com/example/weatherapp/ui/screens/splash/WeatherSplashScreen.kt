package com.example.weatherapp.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.ui.theme.SplashIconBackgroundColor
import com.example.weatherapp.ui.theme.SplashIconColor
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController, scaleValue: Float = 0f) {
    val defaultCity = "Oslo"
    // Animation
    val scale = remember {
        Animatable(scaleValue)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            )

        )
        // delay for 2 seconds
        delay(2000L)
        navController.navigate(WeatherScreens.MainScreen.name+"/$defaultCity")

    })
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.SplashIconBackgroundColor,
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
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.SplashIconColor),
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

@Preview
@Composable
fun WeatherSplashScreenPreview() {
    val navController = rememberNavController()
    WeatherSplashScreen(
        navController = navController,
        scaleValue = 0.9f
    )
}
