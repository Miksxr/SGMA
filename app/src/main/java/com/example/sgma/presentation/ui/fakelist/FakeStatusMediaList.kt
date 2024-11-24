package com.example.sgma.presentation.ui.fakelist

import com.example.sgma.R
import com.example.sgma.data.entity.StatusMedia

fun getFakeStatusMediaList(): List<StatusMedia> {
    return listOf(
        StatusMedia(id = 1, image = R.drawable.breaking_bad, title = "Во все тяжкие", status = "Посмотрел", statusDate = "09.11.2024"),
        StatusMedia(id = 2, image = R.drawable.cyberpunk_2077, title = "Cyberpunk 2077", status = "Посмотрел прохождение", statusDate = "02.11.2024"),
        StatusMedia(id = 3, image = R.drawable.breaking_bad, title = "Во все тяжкие", status = "Смотрю", statusDate = "24.10.2024"),
        StatusMedia(id = 4, image = R.drawable.nash_slon, title = "Зелёный слоник", status = "Посмотрел", statusDate = "17.10.2024"),
        StatusMedia(id = 5, image = R.drawable.kop, title = "Лимонные девочки", status = "В планах", statusDate = "07.10.2024"),
    )
}