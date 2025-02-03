package com.aliozdemir.asparagas.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.asparagas.databinding.ItemHomeNewsBinding
import com.aliozdemir.asparagas.domain.model.Article

class HomeAdapter(
    private val articles: List<Article?>?,
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeNewsBinding.inflate(inflater, parent, false)
        val homeViewHolder = HomeViewHolder(binding)
        return homeViewHolder
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        articles?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }
}
