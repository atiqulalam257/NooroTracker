package com.noorotracker.domain.usecase


import com.noorotracker.data.models.WeatherResponse
import com.noorotracker.data.repository.WeatherRepositoryImpl

class GetWeatherUseCase(private val weatherRepository: WeatherRepositoryImpl) {

    suspend operator fun invoke(city: String): Result<WeatherResponse> {
        return weatherRepository.getWeather(city)
    }
}
