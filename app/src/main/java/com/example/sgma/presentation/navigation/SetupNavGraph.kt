package com.example.sgma.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.domain.comment.viewmodel.CommentViewModel
import com.example.sgma.domain.media.local.viemodel.LocalMediaViewModel
import com.example.sgma.domain.media.remote.game.Game
import com.example.sgma.domain.media.remote.multimedia.Multimedia
import com.example.sgma.domain.media.remote.multimedia.MultimediaViewModel
import com.example.sgma.domain.profile.viewmodel.ProfileViewModel
import com.example.sgma.presentation.ui.screens.RegistrationScreen
import com.example.sgma.presentation.ui.screens.GameDetailScreen
import com.example.sgma.presentation.ui.screens.LoginScreen
import com.example.sgma.presentation.ui.screens.MainScreen
import com.example.sgma.presentation.ui.screens.MultimediaDetailScreen
import com.example.sgma.presentation.ui.screens.SettingsScreen
import com.example.sgma.presentation.ui.fakelist.getFakeMediaList
import com.example.sgma.presentation.ui.screens.FriendsScreen
import com.example.sgma.presentation.ui.screens.ProfileScreen
import com.example.sgma.presentation.ui.screens.RatingsScreen
import com.example.sgma.presentation.ui.screens.RibbonScreen
import com.example.sgma.presentation.ui.screens.StatusesScreen

@Composable
fun CombinedGraph(
    navController: NavHostController,
    localMediaViewModel: LocalMediaViewModel,
    profileViewModel: ProfileViewModel,
    multimediaViewModel: MultimediaViewModel,
    commentViewModel: CommentViewModel,
    context: Context
) {
    val mediaList = getFakeMediaList()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("registration") {
            RegistrationScreen(navController = navController)
        }

        composable("main") {
            MainScreen(navController = navController, multimediaViewModel)
        }
        composable("ribbon") {
            RibbonScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController, profileViewModel, context)
        }
        composable("ratings") {
            RatingsScreen(navController = navController)
        }
        composable("statuses") {
            StatusesScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("friends") {
            FriendsScreen(navController = navController)
        }
        composable("media_list") {
            MainScreen(navController = navController, multimediaViewModel)
        }

        composable("game_detail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
            val game = mediaList.find { it.id == gameId && it.type == ContentTypes.Game }
            game?.let {
                GameDetailScreen(
                    game = Game(
                        id = it.id,
                        name = it.name,
                        image = R.drawable.kop, // пока нету работы с GameAPI
                        year = it.year,
                        sgmaRating = it.sgmaRating,
                        metacritic = it.anotherRating.toInt(), // пока нету работы с GameAPI
                        statusType = it.statusType,
                        description = "" // пока нету работы с GameAPI
                    ),
                    navController = navController,
                    viewModel = localMediaViewModel,
                    context = context
                )
            }
        }
        composable("multimedia_detail/{mediaId}") { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toIntOrNull()
            multimediaViewModel.findMultimedia(mediaId!!)
            var mult by remember { mutableStateOf(Multimedia()) }
            multimediaViewModel.multimedia.observe(context as LifecycleOwner) { media ->
                mult = media
            }
            MultimediaDetailScreen(
                multimedia = mult,
                navController = navController,
                viewModel = localMediaViewModel,
                commentViewModel = commentViewModel,
                context = context
            )
        }
    }
}

