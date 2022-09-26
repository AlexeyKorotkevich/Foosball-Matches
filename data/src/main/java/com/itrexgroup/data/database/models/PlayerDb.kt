package com.itrexgroup.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlayerDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val score: Int
)