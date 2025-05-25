package com.example.shestakov.data

import com.example.shestakov.network.CityService
import com.example.shestakov.network.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefaultAppContainer : AppContainer {

    private val cityRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.api-ninjas.com/")
        .build()

    private val weatherRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.open-meteo.com/")
        .build()

    private val cityService: CityService by lazy {
        cityRetrofit.create(CityService::class.java)
    }

    private val weatherService: WeatherService by lazy {
        weatherRetrofit.create(WeatherService::class.java)
    }

    override val cityWeatherRepository: CityWeatherRepository by lazy {
        NetworkCityWeatherRepository(cityService, weatherService)
    }
}