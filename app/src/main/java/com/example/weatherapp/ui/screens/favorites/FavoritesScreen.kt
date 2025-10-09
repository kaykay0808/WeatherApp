package com.example.weatherapp.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.ui.widgets.WeatherTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                title = " Favorite Cities",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                scrollBehavior = scrollBehavior,
                navController = navController
            ) {
                navController.popBackStack()
            }
        },
        content = { paddingValues ->
            FavoriteListContent(
                modifier = Modifier.padding(paddingValues),
                favoriteViewModel = favoriteViewModel,
                navController = navController
            )
        }
    )
}

@Composable
fun FavoriteListContent(
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel,
    navController: NavController
) {
    Surface(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val list = favoriteViewModel.favList.collectAsState().value
            LazyColumn {
                items(items = list) {
                    FavoriteItem(
                        favoriteData = it,
                        favoriteViewModel = favoriteViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}
