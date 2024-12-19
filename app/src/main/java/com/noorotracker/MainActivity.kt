package com.noorotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.noorotracker.ui.home.HomeScreen
import com.noorotracker.ui.home.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: WeatherViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                viewModel.getSavedCity().collect { city ->
                    if (city.isNotEmpty()) viewModel.loadWeather(city)
                }
            }
            HomeScreen(viewModel)
        }
    }
}
