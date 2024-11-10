package com.example.sgma.presentation.navigation

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.remote.game.Game
import com.example.sgma.domain.media.remote.multimedia.Multimedia
import com.example.sgma.domain.comment.viewmodel.CommentViewModel
import com.example.sgma.domain.media.local.viemodel.LocalMediaViewModel
import com.example.sgma.domain.media.remote.multimedia.MultimediaViewModel
import com.example.sgma.domain.profile.viewmodel.ProfileViewModel
import com.example.sgma.presentation.MainActivity
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
    localMediaViewModel: LocalMediaViewModel,
    profileViewModel: ProfileViewModel,
    commentViewModel: CommentViewModel,
    multimediaViewModel : MultimediaViewModel,
    context: Context
) {
    val mediaList = getFakeMediaList()

    var profile by remember {
        mutableStateOf(profileViewModel.account.value)
    }

    profileViewModel.account.observe(context as LifecycleOwner, {
        profile = it
        if (it.login == MainActivity.userAccountLogin) // if user login
        {
            MainActivity.userProfile = it
        }
    })

    profileViewModel.lastActionResult.observe(context as LifecycleOwner, {
        if (it) {
            profileViewModel.getAccountData(profile?.login ?: "")
        }
        else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    })

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
            MainScreen(navController = navController, multimediaViewModel = multimediaViewModel)
        }
        composable("ribbon") {
            RibbonScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController, MainActivity.userProfile)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("media_list") {
            MainScreen(navController = navController, multimediaViewModel = multimediaViewModel)
        }

        composable("game_detail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
            val game = mediaList.find { it.id == gameId && it.type == ContentTypes.Game }
            game?.let {
                GameDetailScreen(
                    game = Game(
                        id = it.id,
                        name = it.name,
                        image = R.drawable.kop ,//it.image ФЕЙК ФОТО из-за того что media сейчас содержит стринг
                        year = it.year,
                        sgmaRating = it.sgmaRating,
                        metacritic = it.anotherRating,
                        statusType = it.statusType!!,
                        description = "Описание для игры ${it.name}"
                    ),
                    navController = navController,
                    viewModel = localMediaViewModel,
                    commentsViewModel = commentViewModel,
                    profileViewModel = profileViewModel,
                    context = context
                )
            }
        }
        composable("multimedia_detail/{mediaId}") { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toInt()
            multimediaViewModel.findMultimedia(mediaId!!)
            val multimedia = remember { mutableStateOf(multimediaViewModel.multimedia.value) }
            multimediaViewModel.multimedia.observe(context, {
                multimedia.value = it
            })
            multimedia.value.also {
                MultimediaDetailScreen(
                    multimedia = Multimedia(
                        id = it?.id ?: -1,
                        nameRu = it?.nameRu ?: "",
                        image = it?.image ?: "",
                        year = it?.year ?: -1,
                        sgmaRating = it?.sgmaRating ?: 0.0,
                        kinopoiskRating = it?.kinopoiskRating ?: 0.0,
                        description = it?.description ?: "",
                        countries = it?.countries ?: "",
                        genres = it?.genres ?: "",
                        statusType = it?.statusType ?: StatusType.None
                    ),
                    navController = navController,
                    viewModel = localMediaViewModel,
                    commentsViewModel = commentViewModel,
                    profileViewModel = profileViewModel,
                    context = context
                )
            }
        }

        composable("profile/{profileLogin}") { backStackEntry ->
            val profileLogin = backStackEntry.arguments?.getString("profileLogin").toString()
            profileViewModel.getAccountData(profileLogin)
            ProfileScreen(navController, profile)
        }
    }
}

