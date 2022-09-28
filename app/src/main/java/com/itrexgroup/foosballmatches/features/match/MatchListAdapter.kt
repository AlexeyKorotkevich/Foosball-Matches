package com.itrexgroup.foosballmatches.features.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.foosballmatches.R
import com.itrexgroup.foosballmatches.base.BaseAdapter
import com.itrexgroup.foosballmatches.databinding.ItemMatchBinding

class MatchListAdapter : BaseAdapter<MatchDto>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MatchViewHolder) {
            dataList[position].apply {
                holder.bind(this)
                holder.itemView.setOnClickListener { onItemClickListener?.invoke(this) }
                holder.itemView.setOnLongClickListener {
                    onItemLongClickListener?.invoke(this)
                    return@setOnLongClickListener true
                }
            }
        }
    }
}

class MatchViewHolder(private val binding: ItemMatchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(match: MatchDto) {
        binding.tvPlayerOne.text = match.playerOneName
        binding.tvPlayerTwo.text = match.playerTwoName
        binding.tvScoreOne.text = match.playerOneScore.toString()
        binding.tvScoreTwo.text = match.playerTwoScore.toString()
    }

    companion object {
        fun create(parent: ViewGroup): MatchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_match, parent, false)
            val binding = ItemMatchBinding.bind(view)
            return MatchViewHolder(binding)
        }
    }
}