package com.itrexgroup.foosballmatches.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ItemType> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var dataList: MutableList<ItemType> = mutableListOf()

    override fun getItemCount(): Int = dataList.size

    open fun repopulate(list: List<ItemType>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}