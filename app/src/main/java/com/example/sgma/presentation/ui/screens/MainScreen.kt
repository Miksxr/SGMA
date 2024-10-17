package com.example.sgma.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.MediaCard
import com.example.sgma.presentation.ui.SGMAAppBar
import com.example.sgma.presentation.ui.getFakeMediaList

@Composable
fun MainScreen(navController: NavController) {
    val mediaList = getFakeMediaList()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SGMAAppBar(
                onSearchQueryChange = { newQuery -> searchQuery = newQuery },
                searchQuery = searchQuery,
                searchPlaceholder = "Поиск медиа...",
                navController = navController
            )
        },
        bottomBar = {
            Navigation(navController = navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(mediaList.filter { it.name.contains(searchQuery, ignoreCase = true) }) { media ->
                    MediaCard(mediaDBModel = media, onClick = {
                        when (media.type) {
                            ContentTypes.Game -> navController.navigate("game_detail/${media.id}")
                            else -> navController.navigate("multimedia_detail/${media.id}")
                        }
                    })
                }
            }
        }
    }
}








