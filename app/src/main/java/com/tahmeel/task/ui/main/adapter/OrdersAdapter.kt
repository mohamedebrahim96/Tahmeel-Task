package com.tahmeel.task.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import com.tahmeel.task.R
import com.tahmeel.task.databinding.ItemOrderBinding
import com.tahmeel.task.model.Order

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */


class OrdersAdapter : BindingListAdapter<Order, OrdersAdapter.PokemonViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = parent.binding<ItemOrderBinding>(R.layout.item_order)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.binding.apply {
            order = getItem(position)
            executePendingBindings()
        }
    }

    class PokemonViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Order>() {

            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem.loadRef == newItem.loadRef

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem == newItem
        }
    }
}
