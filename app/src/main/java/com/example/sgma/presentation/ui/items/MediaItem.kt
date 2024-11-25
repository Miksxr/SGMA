package com.example.sgma.presentation.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.domain.media.Media
import com.example.sgma.presentation.ui.screens.getLocalizedString

@Composable
fun MediaCard(mediaDBModel: Media, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(130.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(mediaDBModel.image),
                contentDescription = mediaDBModel.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = mediaDBModel.statusType.getLocalizedString(),
                    color = Color.White,
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = mediaDBModel.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${mediaDBModel.sgmaRating}",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.sigma),
                    contentDescription = "Sigma Icon",
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${mediaDBModel.anotherRating}",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                val ratingIcon = if (mediaDBModel.type == ContentTypes.Game) {
                    R.drawable.metacritic
                } else {
                    R.drawable.kinopoisk
                }

                Image(
                    painter = painterResource(id = ratingIcon),
                    contentDescription = "Rating Icon",
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = "${mediaDBModel.type} • ${mediaDBModel.year}",
                fontSize = 16.sp,
            )

            when (mediaDBModel.type) {
                ContentTypes.Anime, ContentTypes.Serial -> {
                    Text(
                        text = "Сезонов: 3, Серий: 15",
                        fontSize = 14.sp,
                    )
                }
                ContentTypes.FILM -> {
                    Text(
                        text = "Длительность: 78 мин",
                        fontSize = 14.sp,
                    )
                }
                ContentTypes.Game -> {
                    Text(
                        text = "Среднее время: 25 часов",
                        fontSize = 14.sp,
                    )
                }

                ContentTypes.Unknown -> {}
                ContentTypes.VIDEO -> {}
                ContentTypes.TV_SERIES -> {}
                ContentTypes.MINI_SERIES -> {}
                ContentTypes.TV_SHOW -> {}
            }
        }
    }
}

@Composable
fun RatingMediaCard2(mediaDBModel: Media, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(130.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(mediaDBModel.image),
                contentDescription = mediaDBModel.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = mediaDBModel.statusType.getLocalizedString(),
                    color = Color.White,
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = mediaDBModel.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${mediaDBModel.sgmaRating}",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.sigma),
                    contentDescription = "Sigma Icon",
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${mediaDBModel.anotherRating}",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                val ratingIcon = if (mediaDBModel.type == ContentTypes.Game) {
                    R.drawable.metacritic
                } else {
                    R.drawable.kinopoisk
                }

                Image(
                    painter = painterResource(id = ratingIcon),
                    contentDescription = "Rating Icon",
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = "${mediaDBModel.type} • ${mediaDBModel.year}",
                fontSize = 16.sp,
            )

            when (mediaDBModel.type) {
                ContentTypes.Anime, ContentTypes.Serial -> {
                    Text(
                        text = "Сезонов: 3, Серий: 15",
                        fontSize = 14.sp,
                    )
                }
                ContentTypes.FILM -> {
                    Text(
                        text = "Длительность: 78 мин",
                        fontSize = 14.sp,
                    )
                }
                ContentTypes.Game -> {
                    Text(
                        text = "Среднее время: 25 часов",
                        fontSize = 14.sp,
                    )
                }

                ContentTypes.Unknown -> {}
                ContentTypes.VIDEO -> {}
                ContentTypes.TV_SERIES -> {}
                ContentTypes.MINI_SERIES -> {}
                ContentTypes.TV_SHOW -> {}
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Оценка пользователя:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "${mediaDBModel.sgmaRating}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.sigma),
                    contentDescription = "Sigma Icon",
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}


