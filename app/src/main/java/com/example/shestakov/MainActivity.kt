package com.example.shestakov

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.shestakov.ui.CityWeatherUI
import com.example.shestakov.ui.theme.ShestakovTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShestakovTheme {
                CityWeatherUI()
            }
        }
    }
}