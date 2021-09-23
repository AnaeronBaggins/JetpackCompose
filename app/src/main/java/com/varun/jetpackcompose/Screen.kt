package com.varun.jetpackcompose

import androidx.annotation.DrawableRes

sealed class Screen(val route : String, val title : String, @DrawableRes val icon : Int) {

    object Home : Screen("home", "Home", R.drawable.outline_home_24)
    object Dashboard : Screen("dashboard", "Dashboard", R.drawable.outline_dashboard_24)
}