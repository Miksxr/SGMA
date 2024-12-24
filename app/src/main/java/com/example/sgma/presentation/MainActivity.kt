package com.example.sgma.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sgma.R
import com.example.sgma.domain.ConnectivityReceiver
import com.example.sgma.domain.comment.viewmodel.CommentViewModel
import com.example.sgma.domain.media.local.viemodel.LocalMediaViewModel
import com.example.sgma.domain.media.remote.multimedia.MultimediaViewModel
import com.example.sgma.domain.profile.viewmodel.ProfileViewModel
import com.example.sgma.presentation.navigation.CombinedGraph
import com.example.sgma.presentation.ui.theme.SGMATheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localMediaViewModel: LocalMediaViewModel

    @Inject
    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var multimediaViewModel: MultimediaViewModel

    @Inject
    lateinit var commentViewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConnectivityReceiver.checkInternetConnection(this@MainActivity)
        setContent {
            SGMATheme {
                Scaffold(content = { paddingValues ->
                    val navController = rememberNavController()
                    if (ConnectivityReceiver.isInternetConnect) {
                        profileViewModel.getAccountData("admin")
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Подключись к интернету!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Box(modifier = Modifier.padding(paddingValues)) {
                        CombinedGraph(
                            navController = navController,
                            localMediaViewModel = localMediaViewModel,
                            profileViewModel = profileViewModel,
                            multimediaViewModel = multimediaViewModel,
                            commentViewModel = commentViewModel,
                            context = this@MainActivity,
                        )
                    }
                })
            }
        }
    }
}


