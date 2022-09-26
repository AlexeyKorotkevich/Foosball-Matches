package com.itrexgroup.domain.dto

data class MatchDto(
    val id: String,
    val playerOneId: String,
    val playerOneScore: Int,
    val playerTwoId: String,
    val playerTwoScore: Int
)