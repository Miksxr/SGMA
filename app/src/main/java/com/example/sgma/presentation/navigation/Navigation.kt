package com.example.sgma.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sgma.presentation.ui.theme.SGMATheme
import com.example.sgma.presentation.ui.theme.Violet

@Composable
fun Navigation(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val listItems = listOf(
        NavigationItem.Home,
        NavigationItem.Ribbon,
        NavigationItem.Profile
    )

    NavigationBar(
        modifier = Modifier.background(Color.Gray)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) MaterialTheme.colorScheme.primary
                        else if (isDarkTheme) Color.White
                        else Color.Black
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.route) MaterialTheme.colorScheme.primary
                        else if (isDarkTheme) Color.White
                        else Color.Black,
                        fontSize = 10.sp
                    )
                }
            )
        }
    }
}