package com.masscode.moviejetpack.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.databinding.FragmentTvShowFavoriteBinding
import com.masscode.moviejetpack.ui.detail.DetailActivity
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentTvShowFavoriteBinding
    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var adapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowFavoriteBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(TvShowFavoriteViewModel::class.java)

        adapter = TvShowFavoriteAdapter { tvShowId, type -> showDetail(tvShowId!!, type) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShows ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(tvShows)
            adapter.notifyDataSetChanged()
        })

        binding.rvTvShow.setHasFixedSize(true)
        binding.rvTvShow.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShows ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(tvShows)
            adapter.notifyDataSetChanged()
        })
    }

    private fun showDetail(tvShowId: Int, type: String?) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TV, tvShowId)
            putExtra(DetailActivity.EXTRA_TYPE, type)
        }
        startActivity(intent)
    }
}