package com.aliozdemir.asparagas.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.asparagas.databinding.FragmentSearchBinding
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearch.text.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.getEverything(
                        query,
                        searchIn = null,
                        sources = null,
                        domains = null,
                        excludeDomains = null,
                        from = null,
                        to = null,
                        language = null,
                        sortBy = null,
                        pageSize = null,
                        page = null,
                    )
                }
                true
            } else {
                false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data.articles?.let { articles ->
                        searchAdapter.submitList(articles)
                    }
                }

                is Resource.Error -> {
                    resource.message.let { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter(::navigateToDetail)
        binding.rvSearchResults.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun navigateToDetail(article: Article) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(article)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
