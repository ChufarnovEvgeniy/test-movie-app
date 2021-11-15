package com.github.chufarnovevgeniy.testmovieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chufarnovevgeniy.testmovieapp.app
import com.github.chufarnovevgeniy.testmovieapp.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(app.moviesRepo)
    }

    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
        initClickListeners()
        initStateObserver()
    }

    private fun initRecyclerView() {
        adapter = MoviesAdapter()

        binding.moviesRecyclerView.adapter = adapter.withLoadStateFooter(
            MoviesLoadStateAdapter {
                viewModel.onRetryClicked()
            }
        )

        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        viewModel.retryEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.retry()
                viewModel.onRetryFinished()
            }
        }
    }

    private fun initClickListeners() {
        binding.retryButton.setOnClickListener {
            viewModel.onRetryClicked()
        }
    }

    private fun initStateObserver() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                binding.root.children.forEach {
                    it.isVisible = false
                }

                when {
                    loadState.source.refresh is LoadState.Loading -> {
                        binding.loadingLayout.isVisible = true
                    }
                    loadState.source.refresh is LoadState.Error -> {
                        binding.errorLayout.isVisible = true
                    }
                    adapter.itemCount != 0 -> {
                        binding.moviesRecyclerView.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}