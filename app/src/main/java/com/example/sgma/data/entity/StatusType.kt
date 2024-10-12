package com.example.sgma.data.entity

// возможные статусы которыми юзер может пометить контент
enum class StatusType {
    // Game
    Completed,
    Played,
    Playing,
    Replaying,
    WatchedWalkthrough,
    HaventPlayed,

    //Movie, TV series, anime Category:
    Watching,
    Watched,
    Rewatching,
    HaventWatched,

    // General
    InPlans
}