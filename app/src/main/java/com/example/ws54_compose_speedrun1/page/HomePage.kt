package com.example.ws54_compose_speedrun1.page

import Current
import DayData
import Forecast
import HourData
import WeatherResponse
import android.annotation.SuppressLint
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ws54_compose_speedrun1.service.*
import com.example.ws54_compose_speedrun1.ui.theme.Averta
import com.example.ws54_compose_speedrun1.widget.HomeAppBar
import com.example.ws54_compose_speedrun1.widget.NavDrawerContent
import kotlinx.coroutines.CoroutineScope
import com.example.ws54_compose_speedrun1.R
object HomePage {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun build(onNavigateToRegion:()->Unit, scope : CoroutineScope, drawerState: DrawerState, data :WeatherResponse){
        val lazyListState = rememberLazyListState()
     ModalDrawer(drawerState = drawerState,drawerContent = {NavDrawerContent.build(scope = scope , drawerState = drawerState, onNavigateToRegion = onNavigateToRegion)}) { //!!! {} lambda
         Scaffold(
             modifier = Modifier.fillMaxSize(),
             topBar = { HomeAppBar.build(scope,drawerState)}
         ) {
             LazyColumn(
                 modifier = Modifier
                     .fillMaxSize()
                     .padding(10.dp, top = 0.dp, 10.dp, 10.dp),
                 horizontalAlignment = Alignment.CenterHorizontally,

                 state = lazyListState
             ) {
                 item{
                     Text(text = "${data.Taichung.current.temp_c}°", fontSize = 80.sp, fontFamily = Averta
                     )
                     Text(text = "${data.Taichung.current.maxTemp_c}° / ${data.Taichung.current.minTemp_c}°", fontSize = 60.sp, fontFamily = Averta)
                     Text(text = "${getTranslatedDescription(description = data.Taichung.current.description)}", fontSize = 40.sp, fontFamily = Averta)
                     Spacer(modifier = Modifier.height(20.dp))
                     _buildTempPerHour(data.Taichung.forecast.hourly)
                     Spacer(modifier = Modifier.height(20.dp))
                     _buildDailyWeather(data.Taichung.forecast.day)
                     Spacer(modifier = Modifier.height(20.dp))
                     _buildPM25(data.Taichung.current)
                 }
             }
         }
     }   
    }

    @Composable
    fun _buildTempPerHour(data: List<HourData>){
        val lazyListState = rememberLazyListState()
        val hoursData = data //list

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(185.dp)
                .clip(RoundedCornerShape(size = 15.dp))
                .background(color = Color.Gray.copy(alpha = 0.5f))
                .padding(5.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                ,horizontalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.hourly_weather), fontFamily = Averta, color = Color.White, fontSize = 15.sp, textAlign = TextAlign.Center)
            }
            Divider(color = Color.White, modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(5.dp))
            LazyRow(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
                state = lazyListState,
            ) {
                items(hoursData.size)
                {
                    _buildPerHourData(data = hoursData[it])
                }
            }
        }
    }

    // 每小時溫度小物件
    @Composable
    fun _buildPerHourData(data:HourData){
        Column(verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = data.time.takeLast(5), fontSize = 15.sp, color = Color.White)
            getIconByDescription(description = data.description)?.let { Image(painter = it,contentDescription = null, modifier = Modifier.size(57.dp)) }
            Text(text = "${data.temp_c}°", fontFamily = Averta, color = Color.White, fontSize = 15.sp)
            Text(text = "${data.daily_chance_of_rain}%", fontFamily = Averta, color = Color.White, fontSize = 15.sp)
        }
    }

    @Composable
    fun _buildDailyWeather(data:List<DayData>){
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(15.dp)
                )
                .background(Color.Gray.copy(alpha = 0.5f))) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = stringResource(id = R.string.weather_in_ten_days), fontFamily = Averta, color = Color.White, fontSize = 15.sp, textAlign = TextAlign.Center)
            }
            Divider(color = Color.White, modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(5.dp))
            data.forEach(){
                _buildDailyWeatherData(it)
            }
        }
    }
    
    @Composable
    fun _buildDailyWeatherData(data:DayData){
        Row() {
            Row(Modifier.width(80.dp)){
                Text(text = data.date, fontFamily = Averta, color = Color.White, fontSize = 15.sp)
            }
            Row(
                Modifier.width(60.dp)
            ){
                getIconByDescription(description = data.description)?.let { Image(painter = it, contentDescription = null,Modifier.size(54.dp)) }
            }
            Row(
                Modifier.width(60.dp)
            ){
                Text(text = "${data.daily_chance_of_rain}%", fontFamily = Averta, color = Color.White, fontSize = 15.sp)
            }
            Row(
                Modifier.width(60.dp)
            ){
                Text(text = "${data.maxTemp_c}°", fontFamily = Averta, color = Color.White, fontSize = 15.sp)
            }
            Row(
                Modifier.width(60.dp)
            ){
                Text(text = "${data.minTemp_c}°", fontFamily = Averta, color = Color.White, fontSize = 15.sp)
            }
        }
    }

    @Composable
    fun _buildPM25(data:Current){
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(15.dp)
                )
                .background(Color.Gray.copy(alpha = 0.5f))) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = "PM2.5", fontFamily = Averta, color = Color.White, fontSize = 15.sp, textAlign = TextAlign.Center)
            }
            Divider(color = Color.White, modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(5.dp))
            Text(text = data.pm25.toString(),color = Color.White, fontSize = 40.sp, fontFamily = Averta, fontWeight = FontWeight.Bold)
            }
        }
    }
