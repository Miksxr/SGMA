package com.example.sgma.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.data.entity.Multimedia

@Composable
fun MultimediaDetailScreen(multimedia: Multimedia, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
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

        Text(
            text = "${multimedia.statusType}",
            fontSize = 20.sp
        )

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
            text = multimedia.description, // Добавьте это поле в класс Multimedia, если его еще нет
            fontSize = 20.sp
        )
    }
}
