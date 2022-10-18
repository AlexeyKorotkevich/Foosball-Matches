package com.itrexgroup.domain.dto

data class PlayerDto(
    val name: String,
    val score: ScoreDto
)

data class ScoreDto(
    var wins: Int = 0,
    var gamesQuantity: Int = 0
)