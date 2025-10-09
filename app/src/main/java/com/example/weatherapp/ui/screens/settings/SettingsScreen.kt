package com.example.weatherapp.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.data.model.local.UnitSetting
import com.example.weatherapp.ui.widgets.WeatherTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {
    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")

    val choiceFromDb =
        settingsViewModel.unitList.collectAsState().value // The database value as state
    val defaultChoice =
        if (choiceFromDb.isNullOrEmpty()) measurementUnits[0] else choiceFromDb[0].unit

    var choiceState by remember { mutableStateOf(defaultChoice) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                title = "Settings",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                scrollBehavior = scrollBehavior,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 15.dp),
                    text = "Change Units of measurement"
                )

                IconToggleButton(
                    checked = !unitToggleState,
                    onCheckedChange = {
                        unitToggleState = !it
                        choiceState = if (unitToggleState) {
                            "Imperial (F)"
                        } else {
                            "Metric (C)"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.4f))
                ) {
                    Text(text = if (unitToggleState) "Fahrenheit ºF" else "Celsius ºC")
                }
                Button(
                    modifier = Modifier
                        .padding(3.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEFBE42)
                    ),
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(UnitSetting(choiceState))
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = stringResource(R.string.save_button),
                        color = Color.White,
                        fontSize = 17.sp,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
