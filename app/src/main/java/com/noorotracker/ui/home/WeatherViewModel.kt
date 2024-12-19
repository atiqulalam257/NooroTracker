package com.noorotracker.ui.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noorotracker.data.models.WeatherResponse
import com.noorotracker.data.repository.WeatherRepositoryImpl
import com.noorotracker.domain.mapper.toDomainModel
import com.noorotracker.domain.model.WeatherData
import com.noorotracker.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<WeatherData?>(null)
    val weather: StateFlow<WeatherData?> = _weather

    fun loadWeather(city: String) {
        viewModelScope.launch {

            getWeatherUseCase.invoke(city).onSuccess {
                _weather.value = it.toDomainModel()
                saveCity(city) // Persist city
            }.onFailure {
                // Handle error
                _weather.value = null
            }
        }
    }

    // DataStore Persistence Code
    @Inject lateinit var dataStore: DataStore<Preferences>

    private suspend fun saveCity(city: String) {
        dataStore.edit { preferences -> preferences[stringPreferencesKey("city")] = city }
    }

    fun getSavedCity() = flow {
        dataStore.data.map { it[stringPreferencesKey("city")] ?: "" }
            .collect { emit(it) }
    }
}
