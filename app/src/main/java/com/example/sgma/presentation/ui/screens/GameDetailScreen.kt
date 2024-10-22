package com.example.sgma.presentation.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.Game
import com.example.sgma.domain.Media
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel

@Composable
fun GameDetailScreen(
    game: Game,
    navController: NavController,
    viewModel: LocalMediaViewModel,
    context: Context
) {
    Column(modifier = Modifier.padding(16.dp)) {
        val inCollectionState = remember { mutableStateOf(false) }
        val statusType = remember { mutableStateOf(game.statusType) }
        val ratingState = remember { mutableStateOf(50f) }

        viewModel.inDB.observe(context as LifecycleOwner) { inDBState ->
            Log.d("LOG", inDBState.toString())
            inCollectionState.value = inDBState
        }

        viewModel.checkMediaInDB(game.id)

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "Назад",
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = game.image),
            contentDescription = game.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = game.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }
        val suggestions = listOf(
            StatusType.Completed.name,
            StatusType.Played.name,
            StatusType.Playing.name,
            StatusType.Replaying.name,
            StatusType.WatchedWalkthrough.name,
            StatusType.HaventPlayed.name,
            StatusType.InPlans.name,
            StatusType.None.name
        )

        Button(onClick = {
            expanded = !expanded
            viewModel.checkMediaInDB(game.id)
        }) {
            Text(statusType.value.name)

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(text = {
                    Text(label)
                }, onClick = {
                    statusType.value = StatusType.valueOf(label)
                    val media = Media(
                        id = game.id,
                        name = game.name,
                        image = game.image,
                        year = game.year,
                        sgmaRating = game.sgmaRating,
                        anotherRating = game.metacritic,
                        type = ContentTypes.Game,
                        statusType = StatusType.valueOf(label)
                    )
                    if (label == StatusType.None.name && inCollectionState.value) {
                        viewModel.deleteMedia(media)
                    } else if (inCollectionState.value) {
                        viewModel.updateStatusType(StatusType.valueOf(label), game.id)
                    } else {
                        viewModel.insertMedia(media)
                    }
                    expanded = false
                })
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "Оцените игру (1-100): ${ratingState.value.toInt()}",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.sigma),
                contentDescription = "Рейтинг",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = ratingState.value,
            onValueChange = { ratingState.value = it },
            valueRange = 1f..100f,
            steps = 98,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(text = "${game.sgmaRating}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.sigma),
                contentDescription = "Рейтинг",
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "${game.metacritic}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.metacritic),
                contentDescription = "Рейтинг",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "США • ${game.year}",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Издатель: Ubisoft",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Жанры: шутер, рпг, песочница",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Описание:",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = game.description,
            fontSize = 20.sp
        )

    }
}