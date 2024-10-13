package com.example.sgma.presentation.ui

import android.content.Context
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.sgma.R
import com.example.sgma.data.entity.Game
import com.example.sgma.domain.media.viemodel.LocalMediaViewModel
import javax.inject.Inject

@Composable
fun GameDetailScreen(
    game: Game,
    navController: NavController,
    viewModel : LocalMediaViewModel,
    context: Context
) {
    Column(modifier = Modifier.padding(16.dp)) {
        val inCollectionState = remember {
            mutableStateOf(false)
        }
        viewModel.inDB.observe(context as LifecycleOwner, {
            inCollectionState.value = it
        })

        Button(
            onClick = { navController.popBackStack() }, // Возврат назад по навигации
            modifier = Modifier
                .align(Alignment.Start)
                .size(80.dp, 40.dp) // Увеличенные размеры кнопки,

        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "back",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        // Большая картинка
        Image(
            painter = painterResource(id = game.image),
            contentDescription = game.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Увеличенный размер изображения
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = game.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))


        if (inCollectionState.value) {
            Text(
                text = "${game.statusType}",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(text = "${game.sgmaRating}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.sigma), // Картинка рейтинга
                contentDescription = "Рейтинг",
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "${game.metacritic}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.metacritic), // Картинка рейтинга
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

        // Описание
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