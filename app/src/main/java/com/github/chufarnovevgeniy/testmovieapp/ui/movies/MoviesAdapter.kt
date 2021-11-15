package com.github.chufarnovevgeniy.testmovieapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chufarnovevgeniy.testmovieapp.R
import com.github.chufarnovevgeniy.testmovieapp.databinding.ItemMovieBinding
import com.github.chufarnovevgeniy.testmovieapp.domain.entities.MovieEntity

class MoviesAdapter :
    PagingDataAdapter<MovieEntity, MoviesAdapter.MovieViewHolder>(REPO_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MovieViewHolder(
        viewGroup: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
    ) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(movie: MovieEntity) {
            binding.titleTextView.text = movie.title
            binding.descriptionTextView.text = movie.description

            movie.multimedia?.imageSrc.let {
                Glide.with(itemView).load(it).into(binding.posterImageView)
            }

            if (movie.multimedia == null) {
                binding.posterImageView.isVisible = false
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.title == newItem.title && oldItem.description == newItem.description

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }
}