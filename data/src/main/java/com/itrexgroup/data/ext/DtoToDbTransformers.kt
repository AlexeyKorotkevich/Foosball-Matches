package com.itrexgroup.data.ext

import com.itrexgroup.data.database.models.MatchDb
import com.itrexgroup.domain.dto.MatchDto

fun MatchDto.transformToMatchDb(): MatchDb {
    return MatchDb(
        id = this.id,
        playerOneName = this.playerOneName,
        playerOneScore = this.playerOneScore,
        playerTwoName = this.playerTwoName,
        playerTwoScore = this.playerTwoScore
    )
}