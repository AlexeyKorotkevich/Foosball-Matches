package com.itrexgroup.domain.dto

data class MatchDto(
    val id: String,
    val playerOneName: String,
    val playerOneScore: Int,
    val playerTwoName: String,
    val playerTwoScore: Int
)