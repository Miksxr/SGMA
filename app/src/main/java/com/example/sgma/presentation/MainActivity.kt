package com.example.sgma.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.sgma.data.datasource.remote.accounts.RemoteAccountDatasourceImpl
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.theme.SGMATheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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


        CoroutineScope(Dispatchers.IO).launch {
            val data = RemoteAccountDatasourceImpl()
            Log.d("LOG", data.getProfileData("admin").toString())
        }
    }
}


