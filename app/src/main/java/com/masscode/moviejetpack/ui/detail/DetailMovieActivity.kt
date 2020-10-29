package com.masscode.moviejetpack.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.databinding.ActivityDetailMovieBinding
import com.masscode.moviejetpack.ui.MainActivity
import com.masscode.moviejetpack.ui.MainActivity.Companion.EXTRA_SCREEN
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_ORIGIN = "extra_origin"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var mMovie: Movie
    private lateinit var viewModel: DetailMovieViewModel
    private var statusFavorite: Boolean = false
    private lateinit var originScreen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        mMovie = intent.getParcelableExtra(EXTRA_MOVIE)!!
        originScreen = intent.getStringExtra(EXTRA_ORIGIN)!!
        statusFavorite = mMovie.isFavorite
        setStatusFavorite(statusFavorite)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailMovieViewModel::class.java)

        with(binding) {
            activity = this@DetailMovieActivity
            lifecycleOwner = this@DetailMovieActivity
            movie = mMovie
            fab.setOnClickListener { fabListener() }
        }
    }

    private fun fabListener() {
        statusFavorite = !statusFavorite
        viewModel.setFavoriteMovie(mMovie, statusFavorite)
        setStatusFavorite(statusFavorite)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        when (originScreen) {
            "movie" -> {
                intent.putExtra(EXTRA_SCREEN, "movie")
                startActivity(intent)
                finish()
            }
            "tv_show" -> {
                intent.putExtra(EXTRA_SCREEN, "tv_show")
                startActivity(intent)
                finish()
            }
            "favorite" -> {
                intent.putExtra(EXTRA_SCREEN, "favorite")
                startActivity(intent)
                finish()
            }
        }
    }
}