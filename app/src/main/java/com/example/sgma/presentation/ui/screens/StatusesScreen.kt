package com.example.sgma.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.presentation.ui.items.MediaCard
import com.example.sgma.presentation.ui.fakelist.getFakeMediaList

@Composable
fun StatusesScreen(navController: NavController, context: Context) {
    val statuses = listOf("Смотрю/Играю", "Посмотрел/Прошёл", "Смотрел/Играл", "Пересматриваю/Переигрываю", "Посмотрел прохождение", "В планах")
    var selectedStatus by remember { mutableStateOf(statuses.first()) }

    val mediaList = getFakeMediaList()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Статусы",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "Назад",
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(statuses) { status ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (status == selectedStatus) MaterialTheme.colorScheme.primary else Color.Gray)
                        .clickable { selectedStatus = status }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = status,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(mediaList) { media ->
                MediaCard(media = media, context = context, onClick = {
                    when (media.type) {
                        ContentTypes.Game -> navController.navigate("game_detail/${media.id}")
                        else -> navController.navigate("multimedia_detail/${media.id}")
                    }
                })
            }
        }
    }
}

