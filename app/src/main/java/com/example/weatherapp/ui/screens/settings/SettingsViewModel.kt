package com.example.weatherapp.ui.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.weather.TemperatureUnitSetting
import com.example.weatherapp.repository.WeatherDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {
    private val _unitList = MutableStateFlow<List<TemperatureUnitSetting>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect { listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {
                        Log.d("TAG", ":Empty list ")
                    } else {
                        _unitList.value = listOfUnits
                    }
                }
        }
    }

    fun insertUnit(unit: TemperatureUnitSetting) =
        viewModelScope.launch { repository.insertUnit(unit) }

    fun updateUnit(unit: TemperatureUnitSetting) =
        viewModelScope.launch { repository.updateUnit(unit) }

    fun deleteUnit(unit: TemperatureUnitSetting) =
        viewModelScope.launch { repository.deleteUnit(unit) }

    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }

}
