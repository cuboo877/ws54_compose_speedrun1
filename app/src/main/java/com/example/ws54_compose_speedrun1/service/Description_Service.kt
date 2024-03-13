package com.example.ws54_compose_speedrun1.service

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ws54_compose_speedrun1.R

@Composable
fun getTranslatedDescription(description: String ): String? {
    val descriptionMap = mapOf(
        "sunny" to stringResource(id = R.string.sunny),
        "rainy" to stringResource(id = R.string.middle_rain),
        "cloudy" to stringResource(id = R.string.cloudy)
    )
    return descriptionMap[description]
}

@Composable
fun getIconByDescription(description: String): Painter? {
    val iconMap = mapOf(
        "sunny" to painterResource(id = R.drawable.morning_sun_sunrise_icon),
        "rainy" to painterResource(id = R.drawable.clouds_cloudy_rain_sunny_icon),
        "cloudy" to painterResource(id = R.drawable.clouds_cloudy_rain_sunny_icon)
    )
    return iconMap[description]
}