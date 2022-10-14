package com.itrexgroup.foosballmatches.features.rankings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrexgroup.domain.dto.ScoreDto
import com.itrexgroup.foosballmatches.R
import com.itrexgroup.foosballmatches.base.BaseAdapter
import com.itrexgroup.foosballmatches.databinding.ItemPlayerBinding

class PlayerRankingAdapter : BaseAdapter<Pair<String, ScoreDto>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerRankingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerRankingViewHolder) {
            dataList[position].apply {
                holder.bind(this, position + 1)
            }
        }
    }
}

class PlayerRankingViewHolder(private val binding: ItemPlayerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(player: Pair<String, ScoreDto>, rank: Int) {
        binding.playerRank.text = itemView.context.getString(R.string.player_rank, rank)
        binding.playerName.text = player.first
        binding.playerGames.text =
            itemView.context.getString(R.string.player_games, player.second.gamesQuantity)
        binding.playerWins.text =
            itemView.context.getString(R.string.player_wins, player.second.score)
    }

    companion object {
        fun create(parent: ViewGroup): PlayerRankingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_player, parent, false)
            val binding = ItemPlayerBinding.bind(view)
            return PlayerRankingViewHolder(binding)
        }
    }
}