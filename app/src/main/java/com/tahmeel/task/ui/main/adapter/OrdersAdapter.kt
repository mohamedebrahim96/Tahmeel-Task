package com.tahmeel.task.ui.main.adapter

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import com.tahmeel.task.R

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */


class OrdersAdapter : BindingListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = parent.binding<ItemPokemonBinding>(R.layout.item_pokemon)
        return PokemonViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    DetailActivity.startActivity(binding.transformationLayout, getItem(position))
                    onClickedAt = currentClickedAt
                }
            }
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.binding.apply {
            pokemon = getItem(position)
            executePendingBindings()
        }
    }

    class PokemonViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}
