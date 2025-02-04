package com.aliozdemir.asparagas.presentation.search

import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.asparagas.databinding.ItemSearchNewsBinding
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.util.loadImage

class SearchViewHolder(private val binding: ItemSearchNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        article: Article,
        onClickListener: (article: Article) -> Unit,
    ) {
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
