package com.example.shestakov.data

interface CityWeatherRepository {
    suspend fun getCityWeather(cityName: String): CityWeather?
}