package com.masscode.moviejetpack.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.databinding.FragmentMovieFavoriteBinding
import com.masscode.moviejetpack.ui.detail.DetailActivity
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

        adapter = MovieFavoriteAdapter { movieId, type -> showDetail(movieId!!, type) }
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

    private fun showDetail(movieId: Int, type: String?) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_MOVIE, movieId)
            putExtra(DetailActivity.EXTRA_TYPE, type)
        }
        startActivity(intent)
    }
}