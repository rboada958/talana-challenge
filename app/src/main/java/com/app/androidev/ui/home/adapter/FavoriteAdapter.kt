package com.app.androidev.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.androidev.app.data.db.entities.FeedItem
import com.app.androidev.databinding.FavoriteLayoutBinding

class FavoriteAdapter(
    private var items: MutableList<FeedItem?>,
) : RecyclerView.Adapter<FavoriteAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(
        private val binding: FavoriteLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedItem) {
            binding.itemHolder = FavoriteViewHolder(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            FavoriteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) =
        holder.bind(items[position]!!)

    override fun getItemCount(): Int =
        items.size

    fun addItems(list: List<FeedItem?>) {
        val positionStart = items.size
        items.addAll(list)
        notifyItemRangeInserted(positionStart, items.size)
    }
}