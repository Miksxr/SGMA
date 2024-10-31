package com.example.sgma.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sgma.R
import com.example.sgma.domain.comment.Comment
import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.Statistic

@Composable
fun CommentCard(
    profile: Profile,
    comment: Comment,
    navController: NavController
) {
    Card(
        modifier = Modifier.padding(5.dp),
    ) {

        Row (modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = profile.image),
                modifier = Modifier
                    .size(68.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("profile/${profile.login}")
                    },
                contentScale = ContentScale.Crop,
                contentDescription = "Profile",
            )

            Column (modifier = Modifier.padding(2.dp)) {
                Text(text = profile.name,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(text = comment.comment,
                    modifier = Modifier
                        .padding(start = 5.dp, bottom = 5.dp),
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp)
            }

        }
    }
}


@Composable
@Preview
fun TestCommentCard() {
    CommentCard(
        navController = rememberNavController(),
        profile = Profile(
        name = "Adolf",
        image = R.drawable.gorin,
        password = "123",
        description = "My acc",
        comments = listOf(),
        friends = listOf(),
        statistic = Statistic(12,12,12,12),
        login = "admin"
    ),
        comment = Comment(
        filmId = 0,
        sgma_rating = 0.0,
        accountName = "admin",
        comment = "Good realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good filmGood realy good film"
        )
    )

}