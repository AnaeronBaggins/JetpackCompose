package com.varun.jetpackcompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(
            text = "Home",
            color = Color.Black)},
        backgroundColor = Color.Transparent)
}