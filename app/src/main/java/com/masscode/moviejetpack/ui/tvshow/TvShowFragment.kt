package com.masscode.moviejetpack.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.databinding.FragmentTvShowBinding
import com.masscode.moviejetpack.ui.detail.DetailTvShowActivity
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowBinding.inflate(inflater)
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(TvShowViewModel::class.java)

        val tvShowAdapter = TvShowAdapter { tvShow -> showDetail(tvShow) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getTvShowList().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = View.GONE
            tvShowAdapter.submitList(movies)
            tvShowAdapter.notifyDataSetChanged()
        })

        binding.rvTvShow.setHasFixedSize(true)
        binding.rvTvShow.adapter = tvShowAdapter
    }

    private fun showDetail(tvShow: TvShow?) {
        val intent = Intent(context, DetailTvShowActivity::class.java).apply {
            putExtra(DetailTvShowActivity.EXTRA_TV, tvShow)
            putExtra(DetailTvShowActivity.EXTRA_ORIGIN_TV, "tv_show")
        }
        startActivity(intent)
    }
}