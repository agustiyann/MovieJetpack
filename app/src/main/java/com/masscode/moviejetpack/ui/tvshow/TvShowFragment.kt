package com.masscode.moviejetpack.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.databinding.FragmentTvShowBinding
import com.masscode.moviejetpack.ui.detail.DetailActivity
import com.masscode.moviejetpack.ui.movie.MovieViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowBinding.inflate(inflater)
        val viewModelFactory = MovieViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, viewModelFactory).get(TvShowViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val movieAdapter = TvShowAdapter { id, type -> showDetail(id!!, type) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getTvShowList().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = View.GONE
            movieAdapter.submitList(movies)
        })

        binding.rvTvShow.adapter = movieAdapter
    }

    private fun showDetail(id: Int, type: String?) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.ID, id)
            putExtra(DetailActivity.TYPE, type)
        }
        startActivity(intent)
    }
}