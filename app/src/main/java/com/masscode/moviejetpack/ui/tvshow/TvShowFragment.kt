package com.masscode.moviejetpack.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTvShowBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val movieAdapter = TvShowAdapter { id, type -> showDetail(id, type) }
        val movies = viewModel.getTvShowList()

        movieAdapter.submitList(movies)
        binding.rvTvShow.adapter = movieAdapter

        return binding.root
    }

    private fun showDetail(id: Int?, type: String?) {

    }
}