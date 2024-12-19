package com.noorotracker.domain.repository

import com.noorotracker.data.models.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(city: String): Result<WeatherResponse>
}