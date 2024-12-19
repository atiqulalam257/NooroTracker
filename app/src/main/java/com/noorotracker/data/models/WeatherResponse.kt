package com.noorotracker.data.models



data class WeatherResponse(
    val location: LocationResponse?,
    val current: CurrentResponse?
)

data class LocationResponse(
    val name: String?,
    val region: String?,
    val country: String?
)

data class CurrentResponse(
    val temp_c: Float?,
    val condition: ConditionResponse?,
    val feelslike_c: Float?,
    val humidity: Int?,
    val uv: Float?
)

data class ConditionResponse(
    val text: String?,
    val icon: String?
)
