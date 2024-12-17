package com.example.weatherapp.ui.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.data.model.local.Favorite
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.ui.screens.favorites.FavoriteViewModel
import com.example.weatherapp.ui.theme.topAppBarTitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true, // what to do
    elevation: Dp = 0.dp,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior,
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    val showIt = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    if (showDialog.value) {
        ShowSettingDropDownMenu(
            showDialog = showDialog,
            navController = navController
        )
    }
    Surface(
        modifier = Modifier.statusBarsPadding(),
        color = Color.Transparent,
        shadowElevation = elevation

    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.topAppBarTitleColor,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                )
            },
            actions = {
                // Checking if we are in the main screen to show some icons.
                if (isMainScreen) {
                    // Create some icons?
                    IconButton(
                        onClick = {
                            onAddActionClicked.invoke()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    }
                    IconButton(
                        onClick = {
                            showDialog.value = true
                        }
                    ) {
                        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "Dots")
                    }
                } else {
                    Box {}
                }
            },
            navigationIcon = {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            onButtonClicked.invoke()
                        }
                    )
                }
                if (isMainScreen) {
                    // Make a variable that check if  its already saved.
                    val isAlreadyFav =
                        favoriteViewModel.favList.collectAsState().value.filter { item ->
                            (item.city == title.split(",")[0])
                            // if it's a match filter will return an array list?
                        }
                    if (isAlreadyFav.isEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite icon",
                            modifier = Modifier
                                .scale(0.9f)
                                .clickable {
                                    val dataList = title.split(",")
                                    // When clicked we want to insert the city and the country
                                    favoriteViewModel.insertFavorite(
                                        // Favorite table
                                        Favorite(
                                            city = dataList[0], // city name
                                            country = dataList[1] // country code
                                        )
                                    ).run {
                                        showIt.value = true
                                    }
                                },
                            tint = Color.Red.copy(alpha = 0.6f)
                        )
                    } else {
                        showIt.value = false
                        Box {}
                    }
                    ShowToast(context = context, showIt)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            scrollBehavior = scrollBehavior
        )
    }
}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    // if showIt is true?
    if (showIt.value) {
        Toast.makeText(
            context,
            "Added to Favorite",
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController,

    ) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf(
        "About",
        "Favorites",
        "Settings"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(
                // Tha padding doesn't change with other factors.
                top = 45.dp,
                right = 20.dp
            )
    ) {
        // something wrong with dropdown after dismissing
        DropdownMenu(
            modifier = Modifier
                .width(140.dp)
                .background(Color.White),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showDialog.value = false
            }
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    text = {
                        Row() {
                            Icon(
                                imageVector = when (text) {
                                    "About" -> Icons.Default.Info
                                    "Favorites" -> Icons.Default.Favorite // favoriteBorder?
                                    else -> Icons.Default.Settings
                                },
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                            Text(
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        when (text) {
                                            "About" -> WeatherScreens.AboutScreen.name
                                            "Favorites" -> WeatherScreens.FavoriteScreen.name
                                            else -> WeatherScreens.SettingsScreen.name
                                        }
                                    )
                                },
                                text = text,
                                fontWeight = FontWeight.W300
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        showDialog.value = false
                    }
                )
            }
        }
    }
}
