package com.masscode.moviejetpack.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.databinding.FragmentMovieFavoriteBinding
import com.masscode.moviejetpack.ui.detail.DetailMovieActivity
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentMovieFavoriteBinding
    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var adapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieFavoriteBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(MovieFavoriteViewModel::class.java)

        adapter = MovieFavoriteAdapter { movie -> showDetail(movie) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(movies)
            adapter.notifyDataSetChanged()
        })

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(movies)
            adapter.notifyDataSetChanged()
        })
    }

    private fun showDetail(movie: Movie?) {
        val intent = Intent(context, DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}