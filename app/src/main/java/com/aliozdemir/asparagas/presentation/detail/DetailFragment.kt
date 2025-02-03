package com.aliozdemir.asparagas.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aliozdemir.asparagas.R
import com.aliozdemir.asparagas.databinding.FragmentDetailBinding
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.util.applySystemInsetsPadding
import com.aliozdemir.asparagas.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        displayArticleDetails(article)

        view.applySystemInsetsPadding(applyLeft = false, applyTop = false, applyRight = false)
    }

    private fun displayArticleDetails(article: Article) {
        with(binding) {
            tvNewsTitle.text = article.title ?: getString(R.string.article_default_title)
            tvNewsDescription.text = article.description ?: getString(R.string.article_default_description)
            tvNewsPublishedAt.text = article.publishedAt ?: getString(R.string.article_default_published_at)
            chipNewsSource.text = article.source?.name ?: getString(R.string.article_default_source)
            tvNewsAuthor.text = article.author ?: getString(R.string.article_default_author)
            tvNewsContent.text = article.content ?: getString(R.string.article_default_content)
            ivNewsImage.loadImage(article.urlToImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
