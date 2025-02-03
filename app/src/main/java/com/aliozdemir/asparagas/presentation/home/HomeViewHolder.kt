package com.aliozdemir.asparagas.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.asparagas.databinding.ItemHomeNewsBinding
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.util.loadImage

class HomeViewHolder(private val binding: ItemHomeNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article, onClickListener: (article: Article) -> Unit) {
        with(binding) {
            tvNewsTitle.text = article.title
            tvNewsDescription.text = article.description
            tvNewsPublishedDate.text = article.publishedAt
            ivNewsImage.loadImage(article.urlToImage)
        }

        binding.root.setOnClickListener {
            onClickListener(article)
        }
    }
}
