package com.masscode.moviejetpack.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.databinding.ActivityDetailBinding
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movie"
        const val EXTRA_TV = "tv"
        const val EXTRA_TYPE = "type"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.movieDetailToolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val type = intent.getStringExtra(EXTRA_TYPE)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        val tvShow = intent.getParcelableExtra<TvShow>(EXTRA_TV)

        if (type == "movie") {
            viewModel.setMovie(movie!!)
            viewModel.movie.observe(this, { data ->
                binding.progressBar.visibility = View.GONE
                binding.movie = data
            })
        } else {
            viewModel.setTvShow(tvShow!!)
            viewModel.tvShow.observe(this, { data ->
                binding.progressBar.visibility = View.GONE
                binding.tvShow = data
            })
        }

        binding.viewModel = viewModel
    }
}