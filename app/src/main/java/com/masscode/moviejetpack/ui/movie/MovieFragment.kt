package com.masscode.moviejetpack.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMovieBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val movieAdapter = MovieAdapter {id, type -> showDetail(id, type)}
        val movies =  viewModel.getMovieList()

        movieAdapter.submitList(movies)
        binding.rvMovies.adapter = movieAdapter

        return binding.root
    }

    private fun showDetail(id: Int?, type: String?) {

    }

}