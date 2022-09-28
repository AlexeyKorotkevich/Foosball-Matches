package com.itrexgroup.foosballmatches.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ItemType> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var dataList: MutableList<ItemType> = mutableListOf()

    protected var onItemClickListener: ((ItemType) -> Unit?)? = null
    protected var onItemLongClickListener: ((ItemType) -> Unit?)? = null

    fun setItemClick(clickListener: (ItemType) -> Unit) {
        onItemClickListener = clickListener
    }

    fun setLongClick(clickListener: (ItemType) -> Unit) {
        onItemLongClickListener = clickListener
    }

    override fun getItemCount(): Int = dataList.size

    open fun repopulate(list: List<ItemType>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}