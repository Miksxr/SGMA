package com.example.sgma.presentation.ui.fakelist

import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.Media

fun getFakeMediaList(): List<Media> {
    return listOf(
        Media(1, "Cyberpunk 2077", R.drawable.cyberpunk_2077, "10 Декабря, 2020", 8.5, 73, "About\n" +
                "Cyberpunk 2077 is a science fiction game loosely based on the role-playing game Cyberpunk 2020.\n" +
                "\n" +
                "Setting\n" +
                "The game is set in the year 2077 in a fictional futuristic metropolis Night City in California. In the world of the game, there are developed cybernetic augmentations that enhance people's strength, agility, and memory. The city is governed by corporations. Many jobs are taken over by the robots, leaving a lot of people poor and homeless. Night City has a roaring underworld, with black markets, underground surgeons, drug dealers, and street gangs abound.\n" +
                "\n" +
                "Characters\n" +
                "The main protagonist is fully customizable, including his or her sex and appearance, and goes by the nickname V. He or she is an underground mercenary who does “dirty business” for the various contractors. An NPC companion named Jackie joins the protagonist early at the game, and various other companions may join the player on certain missions as the plot demands. However, the game has no parties and no companion system.\n" +
                "\n" +
                "Gameplay\n" +
                "The player controls V from the first person view, with the third-person view used for cutscenes only. The protagonist can travel across the city on feet or using various vehicles, in a manner some observers compared to GTA series. There are many options for the character customization, including three character classes, and a variety of augmentations V can install to enhance his or her abilities.", ContentTypes.Game, StatusType.Completed),
        Media(2, "Во все тяжкие", R.drawable.breaking_bad, "2 Марта, 2017", 7.0, 65, "@string/app_name", ContentTypes.Serial, StatusType.Watched),
        Media(3, "Лимонные девочки", R.drawable.kop, "21 Июня, 2006", 9.0, 85, "@string/app_name", ContentTypes.Anime, StatusType.Watching),
        Media(4, "Plants VS Zombies", R.drawable.pvzzz, "1 Января, 2000", 7.0, 65, "@string/app_name", ContentTypes.Game, StatusType.WatchedWalkthrough),
        Media(5, "Зелёный слоник", R.drawable.nash_slon, "14 Мая, 1988", 7.0, 65, "@string/app_name", ContentTypes.Film, StatusType.HaventWatched),
    )
}
