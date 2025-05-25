package com.example.shestakov.data

import com.example.shestakov.network.CityService
import com.example.shestakov.network.WeatherService

class NetworkCityWeatherRepository(
    private val cityService: CityService,
    private val weatherService: WeatherService
) : CityWeatherRepository {

    override suspend fun getCityWeather(cityName: String): CityWeather? {
        val city = cityService.getCityCoordinates(cityName).firstOrNull() ?: return null

        val lat = city.latitude ?: return null
        val lon = city.longitude ?: return null

        val weather = weatherService.getWeather(
            latitude = lat,
            longitude = lon
        )

        return CityWeather(
            city = city.name,
            hourly = weather.hourly
        )
    }
}