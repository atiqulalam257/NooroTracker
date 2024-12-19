package com.noorotracker.data.mapper

import com.noorotracker.data.models.WeatherResponse
import com.noorotracker.domain.model.Condition
import com.noorotracker.domain.model.Current
import com.noorotracker.domain.model.Location
import com.noorotracker.domain.model.WeatherData

fun WeatherResponse.toDomainModel(): WeatherData {
    return WeatherData(
        location = this.location?.let {
            Location(it.name, it.region, it.country)
        },
        current = this.current?.let {
            Current(it.temp_c, it.condition?.let { cond ->
                Condition(cond.text, cond.icon)
            }, it.feelslike_c, it.humidity, it.uv)
        }
    )
}