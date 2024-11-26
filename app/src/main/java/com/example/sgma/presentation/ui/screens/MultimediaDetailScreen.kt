package com.example.sgma.presentation.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.sgma.R
import com.example.sgma.domain.comment.Comment
import com.example.sgma.domain.media.remote.multimedia.Multimedia
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.comment.viewmodel.CommentViewModel
import com.example.sgma.domain.media.local.viemodel.LocalMediaViewModel
import com.example.sgma.presentation.ui.items.CommentCard
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun MultimediaDetailScreen(
    multimedia: Multimedia,
    navController: NavController,
    viewModel: LocalMediaViewModel,
    commentViewModel: CommentViewModel,
    context: Context
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            val inCollectionState = remember { mutableStateOf(false) }
            val statusType = remember { mutableStateOf(multimedia.statusType) }
            val ratingState = remember { mutableFloatStateOf(5.5f) }
            var showDialog by remember { mutableStateOf(false) }
            var isExpanded by remember { mutableStateOf(false) }

            var isImageFullscreen by remember { mutableStateOf(false) }
            var selectedImageIndex by remember { mutableIntStateOf(0) }

            var commentText by remember { mutableStateOf("") }

            viewModel.inDB.observe(context as LifecycleOwner) { inDBState ->
                inCollectionState.value = inDBState
            }

            viewModel.checkMediaInDB(multimedia.id)

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "Назад",
                    modifier = Modifier.size(32.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = rememberAsyncImagePainter(multimedia.image),
                contentDescription = multimedia.nameRu,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = multimedia.nameRu,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                val ratingColor = when (multimedia.ageLimit) {
                    "age0" -> Color.Green
                    "age16" -> Color.Yellow
                    "age18" -> Color.Red
                    else -> Color.Gray
                }

                Column(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .align(Alignment.Top)
                ) {
                    Text(
                        text = multimedia.ageLimit.filter { it.isDigit() } + "+",
                        fontSize = 10.sp,
                        color = ratingColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = ratingColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 4.dp)
                    )
                }
            }

            Text(
                text = multimedia.nameOriginal,
                fontSize = 22.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = statusType.value?.getLocalizedString() ?: "",
                    fontSize = 18.sp,
                )
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(
                            text = "Выберите статус:",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    text = {
                        val categories = listOf(
                            Pair("Посмотрел", StatusType.Watched),
                            Pair("Смотрю", StatusType.Watching),
                            Pair("Пересматриваю", StatusType.Rewatching),
                            Pair("В планах", StatusType.InPlans),
                            Pair("Не смотрел", StatusType.None),
                        )
                        Column {
                            categories.forEach { (label, status) ->
                                val isSelected = statusType.value == status
                                TextButton(onClick = {
                                    statusType.value = status
                                    showDialog = false
                                }) {
                                    Text(
                                        label,
                                        fontSize = 18.sp,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Закрыть", color = Color.Red)
                        }
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${multimedia.sgmaRating}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.sigma),
                    contentDescription = "Рейтинг",
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "${multimedia.kinopoiskRating}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.kinopoisk),
                    contentDescription = "Рейтинг",
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = "Ваша оценка: ${ratingState.floatValue.toDouble()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
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
                value = ratingState.floatValue,
                onValueChange = { newValue ->
                    ratingState.floatValue =
                        ((newValue * 4).roundToInt() / 4f).coerceIn(1f, 10f)
                },
                valueRange = 1f..10f,
                steps = 36,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Дата выхода: ")
                    }
                    append(multimedia.year.toString())
                },
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Длительность: ")
                    }
                    append("${multimedia.length} минуты")
                },
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Страна: ")
                    }
                    append(multimedia.countries)
                },
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Жанры: ")
                    }
                    append(multimedia.genres)
                },
                fontSize = 18.sp
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Скриншоты:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (multimedia.images.isEmpty()) {
                    items(3) {
                        Text(
                            text = "Тут пока ничего нету",
                            fontSize = 20.sp
                        )
                    }
                }
                else {
                    itemsIndexed(multimedia.images) { index, screenshot ->
                        Image(
                            painter = rememberAsyncImagePainter(screenshot),
                            contentDescription = "Скриншоты:",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .clickable {
                                    selectedImageIndex = index
                                    isImageFullscreen = true
                                }
                                .size(150.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }

            if (isImageFullscreen) {
                Dialog(
                    onDismissRequest = { isImageFullscreen = false },
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.8f))
                            .clickable { isImageFullscreen = false }
                    ) {
                        var scale by remember { mutableFloatStateOf(1f) }
                        var offset by remember { mutableStateOf(Offset.Zero) }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .pointerInput(Unit) {
                                    detectTransformGestures { _, pan, zoom, _ ->
                                        scale = (scale * zoom).coerceIn(1f, 3f)
                                        if (scale > 1f) {
                                            offset = Offset(
                                                x = (offset.x + pan.x).coerceIn(-500f, 500f),
                                                y = (offset.y + pan.y).coerceIn(-500f, 500f)
                                            )
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter =
                                rememberAsyncImagePainter(multimedia.images[selectedImageIndex]),
                                contentDescription = "Полный экран скриншота",
                                modifier = Modifier
                                    .graphicsLayer(
                                        scaleX = scale,
                                        scaleY = scale,
                                        translationX = if (scale > 1f) offset.x else 0f,
                                        translationY = if (scale > 1f) offset.y else 0f
                                    )
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f)
                            )
                        }

                        IconButton(
                            onClick = {
                                if (selectedImageIndex - 1 < 0) { selectedImageIndex = multimedia.images.size - 1 }
                                else { selectedImageIndex-- }
                                scale = 1f
                                offset = Offset.Zero
                            },
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_left),
                                contentDescription = "Previous",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                if (selectedImageIndex + 1 > multimedia.images.size - 1) { selectedImageIndex = 0 }
                                else { selectedImageIndex++ }
                                scale = 1f
                                offset = Offset.Zero
                            },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_right),
                                contentDescription = "Next",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Описание:",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = multimedia.description,
                fontSize = 20.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { isExpanded = !isExpanded }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = commentText,
                    onValueChange = {
                        commentText = it
                    },
                    label = { Text(text = "Комментарий", fontSize = 18.sp) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                )
                IconButton(onClick = {
                    commentViewModel.addComments(multimedia.id, Comment(
                        username = "ACC_NAME",
                        text = commentText,
                        date = SimpleDateFormat("dd/M/yyyy hh:mm:ss",
                            Locale.getDefault()).format(Calendar.getInstance().time),
                        avatar = R.drawable.no_user
                    ))
                    commentText = ""
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_send),
                        contentDescription = "Отправить",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Комментарии(3):",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            val comments = listOf(
                Comment(
                    "DevDay",
                    "Режисёр пи*** диди!",
                    "01.01.2024",
                    R.drawable.gorin
                ),
                Comment(
                    "Анна Асти",
                    "ФИЛЬМ ЦАРИЦА!!!",
                    "05.01.2024",
                    R.drawable.gorin
                ),
                Comment("DevNight", "DevDay гад*н", "10.01.2024", R.drawable.gorin)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                comments.forEach { comment ->
                    CommentCard(comment = comment)
                }
            }
        }
    }
}

