package com.example.sgma.data.entity

import android.content.Context
import com.example.sgma.R

// возможные статусы которыми юзер может пометить контент
enum class StatusType(private val resId: Int) {
    // Game
    Completed(R.string.status_completed),
    Played(R.string.status_played),
    Playing(R.string.status_playing),
    Replaying(R.string.status_replaying),
    WatchedWalkthrough(R.string.status_watched_walkthrough),
    HaventPlayed(R.string.status_havent_played),

    //Movie, TV series, anime Category:
    Watching(R.string.status_watching),
    Watched(R.string.status_watched),
    Rewatching(R.string.status_rewatching),
    HaventWatched(R.string.status_havent_watched),

    // General
    InPlans(R.string.status_in_plans),
    None(R.string.status_none);

    fun getLocalizedString(context: Context): String = context.getString(resId)
}
