package com.example.sgma.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.presentation.SGMAAppBar
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.navigation.NavigationItem
import com.example.sgma.presentation.ui.MediaCard
import com.example.sgma.presentation.ui.getFakeMediaList

@Composable
fun RibbonScreen(navController: NavController) {
    val mediaList = getFakeMediaList()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SGMAAppBar(
                title = "SGMA",
                onSettingsClick = { /* Действие для значка настроек */ },
                onSearchQueryChange = { newQuery -> searchQuery = newQuery },
                searchQuery = searchQuery
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