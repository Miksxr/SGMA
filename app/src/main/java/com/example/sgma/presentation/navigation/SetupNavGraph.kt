package com.example.sgma.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.Game
import com.example.sgma.data.entity.Multimedia
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import com.example.sgma.presentation.ui.screens.RegistrationScreen
import com.example.sgma.presentation.ui.screens.GameDetailScreen
import com.example.sgma.presentation.ui.screens.LoginScreen
import com.example.sgma.presentation.ui.screens.MainScreen
import com.example.sgma.presentation.ui.screens.MultimediaDetailScreen
import com.example.sgma.presentation.ui.screens.SettingsScreen
import com.example.sgma.presentation.ui.getFakeMediaList
import com.example.sgma.presentation.ui.screens.ProfileScreen
import com.example.sgma.presentation.ui.screens.RibbonScreen

@Composable
fun CombinedGraph(
    navController: NavHostController,
    viewModel: LocalMediaViewModel,
    context: Context
) {
    val mediaList = getFakeMediaList()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                email = "",
                onEmailChange = {},
                password = "",
                onPasswordChange = {},
                navController = navController
            )
        }
        composable("registration") {
            RegistrationScreen(
                email = "",
                onEmailChange = {},
                navController = navController
            )
        }

        composable("main") {
            MainScreen(navController = navController)
        }
        composable("ribbon") {
            RibbonScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("media_list") {
            MainScreen(navController = navController)
        }

        composable("game_detail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
            val game = mediaList.find { it.id == gameId && it.type == ContentTypes.Game }
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
                    navController = navController,
                    viewModel = viewModel,
                    context = context
                )
            }
        }
        composable("multimedia_detail/{mediaId}") { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toIntOrNull()
            val multimedia = mediaList.find { it.id == mediaId && it.type != ContentTypes.Game }
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
                    navController = navController,
                    viewModel = viewModel,
                    context = context
                )
            }
        }
    }
}

