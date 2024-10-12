package com.example.sgma.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.navigation.Graph
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.navigation.SetupNavGraph
import com.example.sgma.presentation.ui.theme.SGMATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGMATheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { Navigation(navController = navController) }
                ) {
                    CombinedGraph(navController = navController) // Измените на CombinedGraph
                }
            }
        }
    }
}