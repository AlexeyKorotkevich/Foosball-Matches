package com.itrexgroup.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MatchDb(
    @PrimaryKey
    val id: String,
    val playerOneId: String,
    val playerOneScore: Int,
    val playerTwoId: String,
    val playerTwoScore: Int
)