package com.example.sgma.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sgma.R
import com.example.sgma.domain.comment.Comment
import com.example.sgma.domain.comment.viewmodel.CommentViewModel
import com.example.sgma.presentation.MainActivity

@Composable
fun CommentEntryCard(
    commentViewModel: CommentViewModel,
    mediaId : Int
) {
    val ratingState = remember { mutableStateOf(50f) }
    val commentText = remember { mutableStateOf("") }
    
    Row(
        modifier = Modifier.fillMaxWidth().padding(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(1.dp),
            modifier = Modifier.weight(1f)
        ) {
            Column {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Оцените мультимедиа (1-100):",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Text(
                                text = "${ratingState.value.toInt()}",
                                fontSize = 18.sp,
                                //textAlign = TextAlign.Center
                            )
                            Image(
                                painter = painterResource(id = R.drawable.sigma),
                                contentDescription = "Рейтинг",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
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

                OutlinedTextField(
                    value = commentText.value,
                    onValueChange = {
                        commentText.value = it
                    },
                    label = { Text(text = "Ваш комментарий", fontSize = 18.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 0.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Card(
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ) {
            IconButton(
                onClick = {
                    if (commentText.value != "") {
                        val comment = Comment(
                            filmId = mediaId,
                            sgma_rating = ratingState.value / 10.toDouble(),
                            accountName = MainActivity.userAccountLogin,
                            comment = commentText.value
                        )
                        commentViewModel.addComments(mediaId, comment)
                        commentText.value = ""
                        ratingState.value = 50f
                    }
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send_message),
                    contentDescription = "Назад"
                )
            }
        }
    }
}