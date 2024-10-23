package com.example.sgma.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.theme.SGMATheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: LocalMediaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGMATheme {
                val navController = rememberNavController()

                CombinedGraph(
                    navController = navController,
                    viewModel = viewModel,
                    context = this@MainActivity
                )
            }
        }
    }
}


