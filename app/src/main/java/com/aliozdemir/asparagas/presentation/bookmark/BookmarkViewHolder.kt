package com.aliozdemir.asparagas.presentation.bookmark

import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.asparagas.databinding.ItemBookmarkNewsBinding
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.util.loadImage

class BookmarkViewHolder(private val binding: ItemBookmarkNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        article: Article,
        onClickListener: (article: Article) -> Unit,
        onDeleteClickListener: (url: String) -> Unit,
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

        binding.btnNewsDelete.setOnClickListener {
            onDeleteClickListener(article.url!!)
        }
    }
}
