package com.aliozdemir.asparagas.presentation.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aliozdemir.asparagas.databinding.ItemBookmarkNewsBinding
import com.aliozdemir.asparagas.domain.model.Article

class BookmarkAdapter(
    private val onClickListener: (article: Article) -> Unit,
    private val onDeleteClickListener: (url: String) -> Unit,
) : ListAdapter<Article, BookmarkViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookmarkNewsBinding.inflate(inflater, parent, false)
        val bookmarkViewHolder = BookmarkViewHolder(binding)
        return bookmarkViewHolder
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, onClickListener, onDeleteClickListener)
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
