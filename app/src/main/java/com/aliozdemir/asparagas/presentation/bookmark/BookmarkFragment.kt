package com.aliozdemir.asparagas.presentation.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.asparagas.R
import com.aliozdemir.asparagas.databinding.FragmentBookmarkBinding
import com.aliozdemir.asparagas.domain.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookmarkViewModel by viewModels()

    private lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupToolbar()

        viewModel.getAllBookmarkedArticles()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_delete_all -> {
                    viewModel.deleteAllArticles()
                    true
                }
                else -> false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.bookmarkedArticles.observe(viewLifecycleOwner) { articles ->
            bookmarkAdapter.submitList(articles)
        }
    }

    private fun setupRecyclerView() {
        bookmarkAdapter = BookmarkAdapter(::navigateToDetail, ::deleteArticle)
        binding.rvBookmarkNewsList.apply {
            adapter = bookmarkAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun navigateToDetail(article: Article) {
        val action = BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(article)
        findNavController().navigate(action)
    }

    private fun deleteArticle(url: String) {
        viewModel.deleteArticle(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
