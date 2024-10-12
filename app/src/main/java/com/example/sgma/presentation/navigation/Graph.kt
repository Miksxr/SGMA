package com.example.sgma.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sgma.presentation.ui.screens.MainScreen
import com.example.sgma.presentation.ui.getFakeMediaList

@Composable
fun Graph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "Главная") {
        composable("Главная") {
            val mediaList = getFakeMediaList() // Получаем фейковый список медиа
            MainScreen(navController = navHostController)
        }
        composable("Лента") {
            // В будущем: экран ленты
        }
        composable("Профиль") {
            // В будущем: экран профиля
        }
    }
}
