package com.example.shestakov.ui

import com.example.shestakov.data.CityWeather

sealed interface CityWeatherUiState {
    data class Success(val cityWeather: CityWeather) : CityWeatherUiState
    object Error : CityWeatherUiState
    object Loading : CityWeatherUiState
}