package com.example.weatherapp.ui.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.ui.widgets.WeatherTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                title = "About",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                scrollBehavior = scrollBehavior,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.about_app),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.api_used),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = stringResource(id = R.string.author),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}