package com.example.sgma.presentation.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.sgma.data.entity.Multimedia
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.Media
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel

@Composable
fun MultimediaDetailScreen(
    multimedia: Multimedia,
    navController: NavController,
    viewModel : LocalMediaViewModel,
    context: Context
) {
    Column(modifier = Modifier.padding(16.dp)) {
        val inCollectionState = remember {
            mutableStateOf(false)
        }
        val statusType = remember {
            mutableStateOf(multimedia.statusType)
        }
        viewModel.inDB.observe(context as LifecycleOwner, {
            Log.d("LOG", it.toString())
            inCollectionState.value = it
        })

        viewModel.checkMediaInDB(multimedia.id)

        // Кнопка "Назад"
        Button(
            onClick = { navController.popBackStack() }, // Возврат назад по навигации
            modifier = Modifier
                .size(80.dp, 40.dp) // Увеличенные размеры кнопки
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_back), // Иконка "Назад"
                contentDescription = "back"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Большая картинка
        Image(
            painter = painterResource(id = multimedia.image),
            contentDescription = multimedia.nameRu,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Увеличенный размер изображения
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Название мультимедиа (жирное и большое)
        Text(
            text = multimedia.nameRu,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }
        val suggestions = listOf(
            StatusType.Watching.name,
            StatusType.Watched.name,
            StatusType.Rewatching.name,
            StatusType.HaventWatched.name,
            StatusType.InPlans.name,
            StatusType.None.name
        )

        Column {
            Button(onClick = {
                expanded = !expanded
                viewModel.checkMediaInDB(multimedia.id)
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
                            id = multimedia.id,
                            name = multimedia.nameRu,
                            image = multimedia.image,
                            year = multimedia.year,
                            sgmaRating = multimedia.sgmaRating,
                            anotherRating = multimedia.kinopoiskReting,
                            type = ContentTypes.Game,
                            statusType = StatusType.valueOf(label)
                        )
                        if (label == StatusType.None.name && inCollectionState.value) {
                            viewModel.deleteMedia(media)
                        } else if (inCollectionState.value) {
                            viewModel.updateStatusType(StatusType.valueOf(label), multimedia.id)
                        } else {
                            viewModel.insertMedia(media)
                        }
                        expanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(text = "${multimedia.sgmaRating}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.sigma), // Картинка рейтинга
                contentDescription = "Рейтинг",
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "${multimedia.kinopoiskReting}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.kinopoisk), // Картинка рейтинга
                contentDescription = "Рейтинг",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Япония • ${multimedia.year}",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Издатель: Netflix",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Жанры: хоррор, комендия, хентай",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Описание
        Text(
            text = "Описание:",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = multimedia.description,
            fontSize = 20.sp
        )
    }
}
