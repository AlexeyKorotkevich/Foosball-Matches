package com.itrexgroup.data.ext

import com.itrexgroup.data.database.models.MatchDb
import com.itrexgroup.data.database.models.PlayerDb
import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.domain.dto.PlayerDto

fun List<MatchDb>.transformToMatchDto(): List<MatchDto> {
    val result = mutableListOf<MatchDto>()
    this.forEach {
        result.add(
            MatchDto(
                id = it.id,
                playerOneId = it.playerOneId,
                playerOneScore = it.playerOneScore,
                playerTwoId = it.playerTwoId,
                playerTwoScore = it.playerTwoScore
            )
        )
    }
    return result
}

fun List<PlayerDb>.transformToPlayerDto(): List<PlayerDto> {
    val result = mutableListOf<PlayerDto>()
    this.forEach {
        result.add(
            PlayerDto(
                id = it.id,
                name = it.name,
                score = it.score
            )
        )
    }
    return result
}