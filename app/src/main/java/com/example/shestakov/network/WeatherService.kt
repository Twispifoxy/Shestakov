package com.example.shestakov.network

import com.example.shestakov.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("timezone") timezone: String = "auto",
        @Query("forecast_hours") forecast_hours: Int = 24
    ): Weather
}