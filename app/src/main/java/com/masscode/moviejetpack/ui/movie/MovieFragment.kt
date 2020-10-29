package com.masscode.moviejetpack.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.databinding.FragmentMovieBinding
import com.masscode.moviejetpack.ui.detail.DetailMovieActivity
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        val movieAdapter = MovieAdapter { movie-> showDetail(movie!!) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getMovieList().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = View.GONE
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()
        })

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = movieAdapter
    }

    private fun showDetail(movie: Movie) {
        val intent = Intent(context, DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
            putExtra(DetailMovieActivity.EXTRA_ORIGIN, "movie")
        }
        startActivity(intent)
    }

}