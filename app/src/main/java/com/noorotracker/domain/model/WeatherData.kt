package com.noorotracker.domain.model



data class WeatherData(
    val location: Location?,
    val current: Current?
)

data class Location(
    val name: String?,
    val region: String?,
    val country: String?
)

data class Current(
    val temp_c: Float?,
    val condition: Condition?,
    val feelslike_c: Float?,
    val humidity: Int?,
    val uv: Float?
)

data class Condition(
    val text: String?,
    val icon: String?
)
