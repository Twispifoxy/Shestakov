package com.example.shestakov.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shestakov.ui.CityWeatherUiState
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding

@Composable
fun HomeScreen(
    cityWeatherUiState: CityWeatherUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val safeModifier = modifier
        .statusBarsPadding()
        .navigationBarsPadding()

    when (cityWeatherUiState) {
        is CityWeatherUiState.Loading -> LoadingScreen(safeModifier)
        is CityWeatherUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = safeModifier)
        is CityWeatherUiState.Success -> CityWeatherScreen(
            cityWeather = cityWeatherUiState.cityWeather,
            modifier = safeModifier
        )
    }
}
