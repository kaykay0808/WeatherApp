package com.example.weatherapp.ui.widgets

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior,
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
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
                    color = MaterialTheme.colorScheme.onPrimary,
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
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            scrollBehavior = scrollBehavior
        )
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
