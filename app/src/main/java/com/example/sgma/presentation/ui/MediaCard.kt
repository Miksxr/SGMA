package com.example.sgma.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.domain.Media

@Composable
fun MediaCard(mediaDBModel: Media, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(130.dp)
            ) {
                Image(
                    painter = painterResource(id = mediaDBModel.image),
                    contentDescription = mediaDBModel.name,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.7f))
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "${mediaDBModel.statusType}",
                        color = Color.White,
                        fontSize = 8.sp
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

                Text(
                    text = "${mediaDBModel.type} • ${mediaDBModel.year}",
                    fontSize = 18.sp,
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
            }
        }
    }
}

