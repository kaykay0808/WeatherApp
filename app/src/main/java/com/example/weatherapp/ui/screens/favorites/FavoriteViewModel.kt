package com.example.weatherapp.ui.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.local.Favorite
import com.example.weatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getFavorites()
                .distinctUntilChanged()
                .collect { listOfFavorites ->
                    if (listOfFavorites.isEmpty()) {
                        Log.d("EmptyFavorites", "Empty Favorite: $listOfFavorites")
                    } else {
                        _favList.value = listOfFavorites
                        Log.d("Favorites", "Favorite is not empty: ${favList.value}")
                    }
                }
        }
    }

    // Create
    fun insertFavorite(favorite: Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }

    // Read
    fun getFavorites() = viewModelScope.launch { repository.getFavorites() }

    // get Id
    fun getFavById(city: String) = viewModelScope.launch { repository.getFavoriteById(city) }

    // Update
    fun updateFavorite(favorite: Favorite) = viewModelScope.launch { repository.updateFavorite(favorite) }

    // Delete
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite) }

    // Delete all
    fun deleteAllFavorites() = viewModelScope.launch { repository.deleteAllFavorites() }
}