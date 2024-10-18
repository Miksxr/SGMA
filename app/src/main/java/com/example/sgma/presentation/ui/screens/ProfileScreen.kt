package com.example.sgma.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.NewsCard
import com.example.sgma.presentation.ui.SGMAAppBar
import com.example.sgma.presentation.ui.getFakeNewsList

@Composable
fun ProfileScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SGMAAppBar(
                onSearchQueryChange = { newQuery -> searchQuery = newQuery },
                searchQuery = searchQuery,
                searchPlaceholder = "Поиск профилей...",
                navController = navController
            )
        },
        bottomBar = {
            Navigation(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Log.d("LOG", R.drawable.say_my_name.toString())

            Image(
                painter = painterResource(id = R.drawable.say_my_name),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,

                )

            Text(
                text = "Say my name!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Волтер Вайт УНИКИТА",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "7",
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            IconButton(
                onClick = { /* Действие для кнопки "Друзья" */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_friends),
                    contentDescription = "Друзья",
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Статусы:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "Все",
                            color = Color.Red,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .clickable { /* Действие для нажатия на "Все" */ }
                                .padding(8.dp) // Дополнительный отступ
                        )
                    }

                    Column(modifier = Modifier.padding(start = 2.dp)) {
                        Text(text = "Фильмы: 14")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Сериалы: 48")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Аниме: 52")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Игры: 69")
                    }
                }

                Spacer(modifier = Modifier.width(18.dp))

                VerticalDivider(
                    color = Color.Gray.copy(alpha = 0.5f),
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Оценки:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "Все",
                            color = Color.Red,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .clickable { /* Действие для нажатия на "Все" */ }
                                .padding(8.dp) // Дополнительный отступ
                        )
                    }

                    Column(modifier = Modifier.padding(start = 2.dp)) {
                        Text(text = ">90: 82")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "75-90: 34")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "50-75: 1")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "<50: 77")
                    }
                }
            }
        }
    }
}
