package com.aliozdemir.asparagas.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aliozdemir.asparagas.databinding.ItemSearchNewsBinding
import com.aliozdemir.asparagas.domain.model.Article

class SearchAdapter(
    private val onClickListener: (article: Article) -> Unit,
) : ListAdapter<Article, SearchViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchNewsBinding.inflate(inflater, parent, false)
        val searchViewHolder = SearchViewHolder(binding)
        return searchViewHolder
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, onClickListener)
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
