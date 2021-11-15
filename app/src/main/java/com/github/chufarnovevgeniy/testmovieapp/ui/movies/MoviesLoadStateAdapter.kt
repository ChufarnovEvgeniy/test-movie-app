package com.github.chufarnovevgeniy.testmovieapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.chufarnovevgeniy.testmovieapp.R
import com.github.chufarnovevgeniy.testmovieapp.databinding.ItemLoadStateFooterBinding

class MoviesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent)
    }

    class LoadStateViewHolder(
        viewGroup: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(
            viewGroup.context
        ).inflate(R.layout.item_load_state_footer, viewGroup, false)
    ) {
        private val binding = ItemLoadStateFooterBinding.bind(itemView)

        fun bind(loadState: LoadState, retry: () -> Unit) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.errorMessageTextView.isVisible = loadState is LoadState.Error
            binding.retryButton.isVisible = loadState is LoadState.Error

            binding.retryButton.setOnClickListener {
                retry()
            }
        }
    }
}