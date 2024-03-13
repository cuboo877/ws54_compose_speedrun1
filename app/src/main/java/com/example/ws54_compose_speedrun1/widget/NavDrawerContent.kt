package com.example.ws54_compose_speedrun1.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ws54_compose_speedrun1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object NavDrawerContent {
    @Composable
    fun build(scope: CoroutineScope, drawerState: DrawerState, onNavigateToRegion:()->Unit){
        Column(modifier = Modifier.padding(15.dp)) {
            Row{
                IconButton(onClick = { scope.launch { drawerState.close() } }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .paint(
                        painter = painterResource(
                            id = R.drawable.icon,
                        ),
                        contentScale = ContentScale.Crop
                    ).
                        clickable { onNavigateToRegion() },
                horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                Text(text = stringResource(id = R.string.NavDrawerContent_region),color = Color.White, fontSize = 43.sp)
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Color.White,modifier = Modifier.size(40.dp))

            }
        }
    }
}