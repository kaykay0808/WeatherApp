package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.ui.screens.about.AboutScreen
import com.example.weatherapp.ui.screens.favorites.FavoriteViewModel
import com.example.weatherapp.ui.screens.favorites.FavoritesScreen
import com.example.weatherapp.ui.screens.main.MainScreen
import com.example.weatherapp.ui.screens.main.MainViewModel
import com.example.weatherapp.ui.screens.search.SearchScreen
import com.example.weatherapp.ui.screens.settings.SettingsScreen
import com.example.weatherapp.ui.screens.settings.SettingsViewModel
import com.example.weatherapp.ui.screens.splash.WeatherSplashScreen

// Todo: Migrating to the new way of passing arguments.


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        //  www.google.com/cityname="seattle"
        composable(
            /*WeatherScreens.MainScreen.name*/
            route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    city = city
                )
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            // Do something
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name) {
            val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
            FavoritesScreen(
                navController = navController,
                favoriteViewModel = favoriteViewModel
            )
        }
        composable(WeatherScreens.SettingsScreen.name) {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(
                navController = navController,
                settingsViewModel = settingsViewModel
            )
        }
    }
}