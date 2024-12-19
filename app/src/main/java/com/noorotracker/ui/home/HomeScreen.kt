package com.noorotracker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noorotracker.ui.components.WeatherCard
import com.noorotracker.ui.search.SearchBar

@Composable
fun HomeScreen(viewModel: WeatherViewModel) {
    val weather = viewModel.weather.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(onSearch = { city -> viewModel.loadWeather(city) })

        Spacer(modifier = Modifier.height(16.dp))

        WeatherCard(weather)
    }
}
