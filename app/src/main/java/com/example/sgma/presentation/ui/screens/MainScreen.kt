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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.MediaCard
import com.example.sgma.presentation.ui.getFakeMediaList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val mediaList = getFakeMediaList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "SGMA") },
                actions = {
                    IconButton(onClick = { /* Действие для значка настроек */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_settings), // Замените на свою иконку
                            contentDescription = "Настройки"
                        )
                    }
                },
            )
        },
        bottomBar = {
            Navigation(navController = navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            // Поле поиска
            TextField(
                value = "",
                onValueChange = { /* Обработчик изменения текста */ },
                placeholder = { Text(text = "Поиск...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(mediaList) { media ->
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







