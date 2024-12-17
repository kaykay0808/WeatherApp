package com.example.weatherapp.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightModeRowColor = Color(0xFFB2DFDB)
val DarkThemeRowColor = Color(0xFF0D1750)
val Yellowish = Color(0xFFFFC400)

// Splash Screen
val ColorScheme.SplashIconBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else White

val ColorScheme.SplashIconColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) White else Black

// MainScreen
val ColorScheme.mainScreenDaysBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Black else LightModeRowColor

val ColorScheme.mainScreenDaysRowColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkThemeRowColor else White

val ColorScheme.mainScreenDescriptionBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else Yellowish

// Text color for favorite
val ColorScheme.favoriteItemTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Black

val ColorScheme.favoriteItemBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) PurpleGrey40 else LightModeRowColor

val ColorScheme.countryCodeIconBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Black else Pink80

val ColorScheme.deleteIconBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) White else Pink80

val ColorScheme.topAppBarTitleColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Purple40 else Pink80
