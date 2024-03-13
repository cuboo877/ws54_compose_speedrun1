package com.example.ws54_compose_speedrun1.page

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

object RegionPage {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun bulid(onNavigateBack: ()->Unit){
        Scaffold(
            topBar = { regionAppBar(onNavigateBack) }
        ) {

        }
    }

    @Composable
    fun regionAppBar(onNavigateBack:()->Unit){
        TopAppBar(
            title = { Text(text = "地區頁面") },
            navigationIcon = { IconButton(onClick = onNavigateBack){
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            } }
        )
    }
}