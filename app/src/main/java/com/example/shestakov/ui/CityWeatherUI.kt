package com.example.shestakov.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shestakov.ui.screens.HomeScreen
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding

@Composable
fun CityWeatherUI(
    modifier: Modifier = Modifier
) {
    val cityWeatherViewModel: CityWeatherViewModel =
        viewModel(factory = CityWeatherViewModel.Factory)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            MainAppBar(
                text = cityWeatherViewModel.searchTextState.value,
                onTextChange = { cityWeatherViewModel.updateSearchTextState(it) },
                onCloseClicked = {
                    cityWeatherViewModel.updateSearchTextState("")
                },
                onSearchClicked = { cityWeatherViewModel.searchCityWeather(it) }
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HomeScreen(
                cityWeatherUiState = cityWeatherViewModel.cityWeatherUiState,
                retryAction = { cityWeatherViewModel.searchCityWeather(cityWeatherViewModel.searchTextState.value) },
                modifier = modifier
            )
        }
    }
}