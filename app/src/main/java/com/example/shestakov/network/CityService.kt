package com.example.shestakov.network

import com.example.shestakov.City
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.example.shestakov.BuildConfig

interface CityService {
    @GET("v1/city")
    suspend fun getCityCoordinates(
        @Query("name") city: String,
        @Header("X-Api-Key") apiKey: String = BuildConfig.API_NINJAS_KEY
    ): List<City>
}