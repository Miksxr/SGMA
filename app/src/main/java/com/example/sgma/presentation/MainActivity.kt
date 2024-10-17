package com.example.sgma.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.Game
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.Media
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.GameDetailScreen
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
                Scaffold(
                    bottomBar = { Navigation(navController = navController) }
                ) {
                    CombinedGraph(navController = navController, viewModel, this)
                }
            }
        }
    }
}


