package com.example.sgma.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.sgma.domain.ConnectivityReceiver
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import com.example.sgma.domain.profile.viewmodel.ProfileViewModel
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.ui.theme.SGMATheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localMediaViewModel: LocalMediaViewModel

    @Inject
    lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConnectivityReceiver.checkInternetConnection(this@MainActivity)
        setContent {
            SGMATheme {
                val navController = rememberNavController()
                if (ConnectivityReceiver.isInternetConnect) {
                    profileViewModel.getAccountData("admin")
                }
                else {
                    Toast.makeText(this@MainActivity, "Подключись к интернету!!!", Toast.LENGTH_SHORT)
                        .show()
                }
                CombinedGraph(
                    navController = navController,
                    localMediaViewModel = localMediaViewModel,
                    profileViewModel = profileViewModel,
                    context = this@MainActivity
                )
            }
        }
    }
}


