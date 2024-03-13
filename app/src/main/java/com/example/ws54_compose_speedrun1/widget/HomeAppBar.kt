package com.example.ws54_compose_speedrun1.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.ws54_compose_speedrun1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object HomeAppBar{
    @Composable
    fun build(scope:CoroutineScope,drawerState: DrawerState){
        TopAppBar(
            title = { Row() {
                Text(text = stringResource(id = R.string.current_location))
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            } },
            navigationIcon = { IconButton(onClick = { scope.launch { drawerState.open() } }){
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            } },

        )
    }
}