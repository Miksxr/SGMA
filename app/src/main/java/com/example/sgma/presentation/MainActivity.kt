package com.example.sgma.presentation

import android.content.Intent
import android.content.IntentFilter
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.sgma.R
import com.example.sgma.data.datasource.remote.accounts.RemoteAccountDatasourceImpl
import com.example.sgma.domain.ConnectivityReceiver
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.Statistic
import com.example.sgma.domain.profile.viewmodel.ProfileItemViewModel
import com.example.sgma.domain.profile.viewmodel.ProfileViewModel
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.navigation.Navigation
import com.example.sgma.presentation.ui.theme.SGMATheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: LocalMediaViewModel

    @Inject
    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var profileItemViewModel: ProfileItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGMATheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { Navigation(navController = navController) }
                ) {
                    CombinedGraph(navController = navController, viewModel, profileViewModel,this)
                }
            }
        }

        this.registerReceiver(
            ConnectivityReceiver(),
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        )

            // registration example
//        profileViewModel.registerAccount(Profile(
//            name = "Lalka",
//            image = R.drawable.gorin,
//            password = "LALKA",
//            description = "Siska",
//            comments = emptyList(),
//            friends = listOf("admin"),
//            statistic = Statistic(12, 12, 12, 12),
//            login = "acc3"
//        ))
    }
}


