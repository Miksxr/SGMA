package com.example.sgma.domain.comment

import com.example.sgma.domain.profile.Profile

data class CommentItem (
    val profile: Profile,
    val comment: Comment
)