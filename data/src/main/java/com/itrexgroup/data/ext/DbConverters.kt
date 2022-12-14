package com.itrexgroup.data.ext

import com.itrexgroup.data.database.models.MatchDb
import com.itrexgroup.domain.dto.MatchDto

fun List<MatchDb>.transformToMatchDto(): List<MatchDto> {
    val result = mutableListOf<MatchDto>()
    this.forEach {
        result.add(
            MatchDto(
                id = it.id,
                playerOneName = it.playerOneName,
                playerOneScore = it.playerOneScore,
                playerTwoName = it.playerTwoName,
                playerTwoScore = it.playerTwoScore
            )
        )
    }
    return result
}

fun MatchDto.transformToMatchDb(): MatchDb {
    return MatchDb(
        id = this.id,
        playerOneName = this.playerOneName,
        playerOneScore = this.playerOneScore,
        playerTwoName = this.playerTwoName,
        playerTwoScore = this.playerTwoScore
    )
}