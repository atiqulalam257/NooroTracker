package com.noorotracker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.noorotracker.domain.model.WeatherData

@Composable
fun WeatherCard(weather: WeatherData?) {
    if (weather == null) {
        // No City Selected State
        Text(
            text = "No City Selected",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Please Search For A City",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    } else {
        // City Selected State
        Image(
            painter = rememberAsyncImagePainter("https:" +weather.current?.condition?.icon),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = weather.location?.name ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${weather.current?.temp_c}°C",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.height(75.dp).background(Color(0XffF2F2F2), RoundedCornerShape(8.dp))){
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight().align(Alignment.Center)
                    .background(Color(0XffF2F2F2), RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDetail(label = "Humidity", value = "${weather.current?.humidity}%")
                WeatherDetail(label = "UV", value = "${weather.current?.uv}")
                WeatherDetail(label = "Feels Like", value = "${weather.current?.feelslike_c}°C")
            }
        }


    }
}

@Composable
fun WeatherDetail(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}