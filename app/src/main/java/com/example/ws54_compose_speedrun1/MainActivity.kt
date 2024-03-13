package com.example.ws54_compose_speedrun1

import WeatherResponse
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ws54_compose_speedrun1.page.HomePage
import com.example.ws54_compose_speedrun1.page.RegionPage
import com.example.ws54_compose_speedrun1.service.JsonService
import com.example.ws54_compose_speedrun1.ui.theme.Ws54_compose_speedrun1Theme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ws54_compose_speedrun1Theme {
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // !!! DrawerValue.Closed

                val jsonService = JsonService
                val jsonString = jsonService.readJsonFromAssets(this,"weatherData.json")
                val data = JsonService.parseJson(jsonString)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppScreen(scope, drawerState,data)
                }
            }
        }
    }

    @Composable
    fun AppScreen(scope:CoroutineScope, drawerState: DrawerState,data:WeatherResponse){
        var currentScreen by remember { mutableStateOf("Home") }

        when (currentScreen){
            "Home" -> HomePage.build({currentScreen = "Region"}, scope = scope, drawerState = drawerState, data = data)
            "Region" -> RegionPage.bulid({currentScreen = "Home"})
        }
    }
}
