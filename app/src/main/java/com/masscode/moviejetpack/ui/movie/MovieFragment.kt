package com.masscode.moviejetpack.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.databinding.FragmentMovieBinding
import com.masscode.moviejetpack.ui.detail.DetailActivity

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater)
        val viewModelFactory = MovieViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val movieAdapter = MovieAdapter { id, type -> showDetail(id!!, type) }
        viewModel.getMovieList().observe(viewLifecycleOwner, { movies ->
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()
        })

        binding.rvMovies.adapter = movieAdapter
    }

    private fun showDetail(id: Int, type: String?) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.ID, id)
            putExtra(DetailActivity.TYPE, type)
        }
        startActivity(intent)
    }

}