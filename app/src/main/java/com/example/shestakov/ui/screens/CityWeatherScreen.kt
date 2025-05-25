package com.example.shestakov.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shestakov.data.CityWeather

@Composable
fun CityWeatherScreen(
    cityWeather: CityWeather?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = cityWeather?.city ?: "Неизвестный город",
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 40.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        val hourly = cityWeather?.hourly
        if (hourly != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val times = hourly.time
                val temps = hourly.temperature2m
                val count = minOf(times.size, temps.size)
                for (i in 0 until count) {
                    val hourOnly = times[i].substringAfter("T")
                    val temperature = temps[i]
                    val prevTemp = if (i > 0) temps[i - 1] else null
                    Card(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(width = 100.dp, height = 140.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = hourOnly,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Icon(
                                imageVector = temperatureChangeIcon(temperature, prevTemp),
                                contentDescription = "Temp change icon",
                                tint = temperatureChangeColor(temperature, prevTemp),
                                modifier = Modifier.size(36.dp)
                            )
                            Text(
                                text = "$temperature°C",
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}


fun temperatureChangeIcon(currentTemp: Double, previousTemp: Double?): ImageVector {
    return when {
        previousTemp == null -> Icons.Default.Remove
        currentTemp > previousTemp -> Icons.Default.ArrowDropUp
        currentTemp < previousTemp -> Icons.Default.ArrowDropDown
        else -> Icons.Default.Remove
    }
}

fun temperatureChangeColor(currentTemp: Double, previousTemp: Double?): Color {
    return when {
        previousTemp == null -> Color.Gray
        currentTemp > previousTemp -> Color.Red
        currentTemp < previousTemp -> Color.Blue
        else -> Color.Gray
    }
}
