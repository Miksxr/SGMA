package com.example.sgma.presentation.ui.fakelist

import com.example.sgma.R
import com.example.sgma.data.entity.RatingMedia

fun getFakeRatingMediaList(): List<RatingMedia> {
    return listOf(
        RatingMedia(id = 1, image = R.drawable.breaking_bad, title = "Во все тяжкие", rating = "5.5", ratingDate = "09.11.2024"),
        RatingMedia(id = 2, image = R.drawable.cyberpunk_2077, title = "Cyberpunk 2077", rating = "7.75", ratingDate = "02.11.2024"),
        RatingMedia(id = 3, image = R.drawable.pvzzz, title = "Во все тяжкие", rating = "7.0", ratingDate = "24.10.2024"),
        RatingMedia(id = 4, image = R.drawable.nash_slon, title = "Зелёный слоник", rating = "6.25", ratingDate = "17.10.2024"),
        RatingMedia(id = 5, image = R.drawable.kop, title = "Лимонные девочки", rating = "9.75", ratingDate = "07.10.2024"),
    )
}