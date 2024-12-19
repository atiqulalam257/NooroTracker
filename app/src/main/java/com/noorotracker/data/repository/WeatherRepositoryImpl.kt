package com.noorotracker.data.repository

import com.noorotracker.BuildConfig
import com.noorotracker.data.api.WeatherService
import com.noorotracker.data.models.WeatherResponse
import com.noorotracker.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
) : WeatherRepository {
    override suspend fun getWeather(city: String): Result<WeatherResponse> {
        return try {
            val response = service.getWeather(BuildConfig.weatherApiKey, city)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
