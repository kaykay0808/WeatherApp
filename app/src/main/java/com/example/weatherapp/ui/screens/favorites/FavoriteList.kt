package com.example.weatherapp.ui.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.model.local.Favorite
import com.example.weatherapp.navigation.WeatherScreens

@Composable
fun FavoriteList(

) {

}

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    favoriteData: Favorite,
    favoriteViewModel: FavoriteViewModel,
    navController: NavController
) {
    Surface(
        modifier = modifier
            .padding(3.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clickable {
                // Todo: Whatever is clicked in favorite will move us back to that city in mainScreen
                navController.navigate(WeatherScreens.MainScreen.name + "/${favoriteData.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xFFB2DFDB)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // City
            Text(
                text = favoriteData.city,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(start = 20.dp) // 4.dp
            )
            // Country
            Surface(
                modifier = Modifier
                    .padding(start = 0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = favoriteData.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            // Delete Icon
            Icon(
                modifier = Modifier
                    .padding(end = 20.dp) // 4.dp
                    .clickable {
                        favoriteViewModel.deleteFavorite(favoriteData)
                    },
                imageVector = Icons.Rounded.Delete,
                tint = Color.Red.copy(alpha = 0.3f),
                contentDescription = "Delete Icon"
            )
        }
    }
}

@Preview
@Composable
fun FavoriteItemPreview() {
    val mockNavController = rememberNavController()
    val mockFavoriteViewModel: FavoriteViewModel = hiltViewModel()


    FavoriteItem(
        favoriteData = Favorite(
            city = "Oslo",
            country = "No",
        ),
        favoriteViewModel = mockFavoriteViewModel,
        navController = mockNavController,
    )
}
