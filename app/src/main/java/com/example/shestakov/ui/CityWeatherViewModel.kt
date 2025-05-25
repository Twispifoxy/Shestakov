package com.example.shestakov.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shestakov.CityWeatherApplication
import com.example.shestakov.data.CityWeatherRepository
import kotlinx.coroutines.launch

class CityWeatherViewModel(
    private val cityWeatherRepository: CityWeatherRepository
) : ViewModel() {

    var cityWeatherUiState: CityWeatherUiState by mutableStateOf(CityWeatherUiState.Loading)
        private set

    private val _searchTextState = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    init {
        loadCityWeather("Izhevsk")
    }

    private fun loadCityWeather(cityName: String) {
        viewModelScope.launch {
            cityWeatherUiState = CityWeatherUiState.Loading
            cityWeatherUiState = try {
                val result = cityWeatherRepository.getCityWeather(cityName)
                if (result != null) CityWeatherUiState.Success(result)
                else CityWeatherUiState.Error
            } catch (e: Exception) {
                CityWeatherUiState.Error
            }
        }
    }

    fun searchCityWeather(query: String) {
        loadCityWeather(query)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CityWeatherApplication)
                CityWeatherViewModel(application.container.cityWeatherRepository)
            }
        }
    }
}