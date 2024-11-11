package com.example.weatherapp.ui.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.ui.widgets.WeatherTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController
) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            WeatherTopAppBar(
                navController = navController,
                title = "Search",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                scrollBehavior = scrollBehavior,
            ) {
                navController.popBackStack()
            }
        },
        content = { paddingValues ->
            Surface {
                Column(
                    modifier = Modifier
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        searchQueryState = searchQueryState,
                        valid = valid,
                        keyboardController = keyboardController
                    ) { mCity ->
                        Log.d("SearchTag", "SearchScreen: $mCity")
                        navController.navigate(WeatherScreens.MainScreen.name + "/$mCity")
                    }
                }
            }
        }
    )
}

@Composable
fun SearchBar(
    modifier: Modifier, // Move this down later to commonTextField
    searchQueryState: MutableState<String>,
    valid: Boolean,
    keyboardController: SoftwareKeyboardController?,
    onSearch: (String) -> Unit = {}// The city name?
) {
    Column {
        CommonTextField(
            modifier = modifier,
            valueState = searchQueryState,
            placeholder = "Type a city",
            onAction = KeyboardActions {
                // When we start typing
                // Checking if what we typed in is valid (is not valid means it's empty)
                if (!valid) {
                    return@KeyboardActions
                } else {
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value = "" // Just to clear everything up
                    keyboardController?.hide()
                }
            }
        )
    }
}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search, // or Next?
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        value = valueState.value,
        onValueChange = {
            valueState.value = it // How do we get this city name to our main screen?
        },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp)
    )
}
