package com.example.sgma.presentation.navigation

import com.example.sgma.R

sealed class NavigationItem(val title: String, val iconId: Int, val route: String) {
    data object Home : NavigationItem("Главная", R.drawable.icon_home, "main")
    data object Ribbon : NavigationItem("Лента", R.drawable.icon_ribbon, "ribbon")
    data object Profile : NavigationItem("Профиль", R.drawable.icon_profile, "profile")
}