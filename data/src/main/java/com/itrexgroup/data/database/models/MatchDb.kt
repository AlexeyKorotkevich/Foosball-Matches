package com.itrexgroup.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MatchDb(
    @PrimaryKey
    val id: String,
    val playerOneName: String,
    val playerOneScore: Int,
    val playerTwoName: String,
    val playerTwoScore: Int
)