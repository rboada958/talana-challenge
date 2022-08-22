package com.app.androidev.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.androidev.app.model.feeds.FeedItem
import com.app.androidev.databinding.FeedLayoutBinding

class FeedAdapter(
    private var items: MutableList<FeedItem?>,
    private val listener: OnFeedClickListener? = null
) : RecyclerView.Adapter<FeedAdapter.FeedsViewHolder>() {

    class FeedsViewHolder(
        private val binding: FeedLayoutBinding,
        private val listener: OnFeedClickListener? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedItem) {
            binding.itemHolder = FeedViewHolder(item) {
                listener?.onFeedClicked(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder =
        FeedsViewHolder(
            FeedLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) =
        holder.bind(items[position]!!)

    override fun getItemCount(): Int =
        items.size

    fun addItems(list: List<FeedItem?>) {
        val positionStart = items.size
        items.addAll(list)
        notifyItemRangeInserted(positionStart, items.size)
    }

    interface OnFeedClickListener {
        fun onFeedClicked(item: FeedItem)
    }

}