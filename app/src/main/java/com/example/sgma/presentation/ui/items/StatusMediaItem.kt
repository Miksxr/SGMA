package com.example.sgma.presentation.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sgma.data.entity.StatusMedia

@Composable
fun StatusMediaCard(media: StatusMedia) {
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp, top = 8.dp, start = 4.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(media.image),
            contentDescription = null,
            modifier = Modifier
                .width(50.dp)
                .height(65.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )


        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = media.title,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )

            Text(
                text = media.status,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = media.statusDate,
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}