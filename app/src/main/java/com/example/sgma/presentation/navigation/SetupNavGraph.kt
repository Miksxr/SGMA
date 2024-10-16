package com.example.sgma.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.Game
import com.example.sgma.data.entity.Multimedia
import com.example.sgma.presentation.ui.GameDetailScreen
import com.example.sgma.presentation.ui.screens.MainScreen
import com.example.sgma.presentation.ui.MultimediaDetailScreen
import com.example.sgma.presentation.ui.getFakeMediaList

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val mediaList = getFakeMediaList()

    NavHost(
        navController = navController,
        startDestination = "media_list"
    ) {
        composable("media_list") {
            MainScreen(navController = navController)
        }
        composable("game_detail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
            val game = mediaList.find { it.id == gameId && it.type == ContentTypes.GAME }
            game?.let {
                GameDetailScreen(game = Game(
                    id = it.id,
                    name = it.name,
                    image = it.image,
                    year = it.year,
                    sgmaRating = it.sgmaRating,
                    metacritic = it.anotherRating,
                    statusType = it.statusType,
                    description = "Описание для игры ${it.name}"
                ), navController
                )
            }
        }
        composable("multimedia_detail/{mediaId}") { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toIntOrNull()
            val multimedia = mediaList.find { it.id == mediaId && it.type != ContentTypes.GAME }
            multimedia?.let {
                MultimediaDetailScreen(multimedia = Multimedia(
                    id = it.id,
                    nameRu = it.name,
                    image = it.image,
                    year = it.year,
                    sgmaRating = it.sgmaRating,
                    kinopoiskReting = it.anotherRating,
                    statusType = it.statusType,
                    description = "Описание для мультимедия ${it.name}"
                ), navController
                )
            }
        }
    }
}

@Composable
fun CombinedGraph(navController: NavHostController) {
    val mediaList = getFakeMediaList() // Получаем фейковый список медиа

    NavHost(navController = navController, startDestination = "Главная") {
        // Первый граф
        composable("Главная") {
            MainScreen(navController = navController)
        }
        composable("Лента") {
            // В будущем: экран ленты
        }
        composable("Профиль") {
            // В будущем: экран профиля
        }

        // Второй граф
        composable("media_list") {
            MainScreen(navController = navController)
        }
        composable("game_detail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
            val game = mediaList.find { it.id == gameId && it.type == ContentTypes.GAME }
            game?.let {
                GameDetailScreen(
                    game = Game(
                        id = it.id,
                        name = it.name,
                        image = it.image,
                        year = it.year,
                        sgmaRating = it.sgmaRating,
                        metacritic = it.anotherRating,
                        statusType = it.statusType,
                        description = "Описание для игры ${it.name}"
                    ),
                    navController = navController // Убедитесь, что здесь правильный параметр
                )
            }
        }
        composable("multimedia_detail/{mediaId}") { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toIntOrNull()
            val multimedia = mediaList.find { it.id == mediaId && it.type != ContentTypes.GAME }
            multimedia?.let {
                MultimediaDetailScreen(
                    multimedia = Multimedia(
                        id = it.id,
                        nameRu = it.name,
                        image = it.image,
                        year = it.year,
                        sgmaRating = it.sgmaRating,
                        kinopoiskReting = it.anotherRating,
                        statusType = it.statusType,
                        description = "Описание для мультимедия ${it.name}"
                    ),
                    navController = navController // Убедитесь, что здесь правильный параметр
                )
            }
        }
    }
}

