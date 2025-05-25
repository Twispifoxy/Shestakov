package com.example.shestakov

import android.app.Application
import com.example.shestakov.data.AppContainer
import com.example.shestakov.data.DefaultAppContainer

class CityWeatherApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}