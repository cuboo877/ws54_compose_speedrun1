package com.example.ws54_compose_speedrun1.service

import WeatherResponse
import android.content.ClipDescription
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ws54_compose_speedrun1.R
import com.google.gson.Gson

//!!!
object JsonService {
    fun readJsonFromAssets(context:Context, fileName:String):String{
        return context.assets.open(fileName).bufferedReader().use{it.readText()}
    }

    fun parseJson(jsonString:String):WeatherResponse {
        val gson = Gson()
        return gson.fromJson(jsonString,WeatherResponse::class.java)
    }


}