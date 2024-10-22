package com.example.sgma.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sgma.R
import kotlinx.serialization.json.JsonNull.content

@Composable
fun SettingsScreen(navController: NavController) {
    var versionClickCount by remember { mutableStateOf(0) }
    var showImage by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Настройки",
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

    }, bottomBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /* Действие для кнопки "Выйти из аккаунта" */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Выйти из аккаунта", color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Версия 0.0.1 Beta", modifier = Modifier.clickable {
                versionClickCount++
                if (versionClickCount >= 3) {
                    showImage = true
                    versionClickCount = 0
                }
            })
        }
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = if (isDarkMode) "Темный режим" else "Светлый режим")

                Spacer(modifier = Modifier.width(8.dp))

                Switch(checked = isDarkMode, onCheckedChange = {
                    isDarkMode = it
                    // Здесь можно добавить код для изменения темы приложения
                })
            }
        }

        if (showImage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .clickable { showImage = false }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.leonid),
                    contentDescription = "Секретное изображение",
                    modifier = Modifier.size(400.dp)
                )
            }
        }
    })
}
