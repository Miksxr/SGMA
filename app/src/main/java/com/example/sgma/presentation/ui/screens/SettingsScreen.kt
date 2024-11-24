package com.example.sgma.presentation.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.presentation.ui.theme.SGMATheme
import com.example.sgma.presentation.ui.theme.Violet
import kotlinx.serialization.json.JsonNull.content

@Composable
fun SettingsScreen(navController: NavController) {
    var themeDialogOpen by remember { mutableStateOf(false) }
    var selectedTheme by remember { mutableStateOf(ThemeOption.System) }

    SGMATheme {
        Scaffold(
            topBar = {
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
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { themeDialogOpen = true }
                            .padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_dark),
                            contentDescription = "Иконка темы",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = when (selectedTheme) {
                                ThemeOption.System -> "По устройству"
                                ThemeOption.Light -> "Светлая тема"
                                ThemeOption.Dark -> "Тёмная тема"
                            },
                            fontSize = 18.sp
                        )
                    }
                }
            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Выйти из аккаунта")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val context = LocalContext.current

                        Image(
                            painter = painterResource(id = R.drawable.telegram),
                            contentDescription = "Telegram",
                            modifier = Modifier
                                .size(45.dp)
                                .clickable {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://t.me/+HsWGcsly8DY0MmRi")
                                    )
                                    context.startActivity(intent)
                                }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Версия 0.0.1 Beta"
                        )
                    }
                }
            }
        )

        if (themeDialogOpen) {
            ThemeSelectionDialog(
                currentTheme = selectedTheme,
                onDismiss = { themeDialogOpen = false },
                onThemeSelected = { selectedTheme = it }
            )
        }
    }
}

enum class ThemeOption { System, Light, Dark }

@Composable
fun ThemeSelectionDialog(
    currentTheme: ThemeOption,
    onDismiss: () -> Unit,
    onThemeSelected: (ThemeOption) -> Unit
) {
    var selectedOption by remember { mutableStateOf(currentTheme) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Выберите тему:",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            val options = listOf(
                Pair("По устройству", ThemeOption.System),
                Pair("Светлая тема", ThemeOption.Light),
                Pair("Тёмная тема", ThemeOption.Dark)
            )
            Column {
                options.forEach { (label, theme) ->
                    val isSelected = theme == currentTheme
                    TextButton(onClick = {
                        selectedOption = theme
                        onThemeSelected(theme)
                        onDismiss()
                    }) {
                        Text(
                            text = label,
                            fontSize = 18.sp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Закрыть", color = Color.Red)
            }
        }
    )
}