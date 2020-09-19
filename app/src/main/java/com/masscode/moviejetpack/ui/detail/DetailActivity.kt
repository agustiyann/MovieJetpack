package com.masscode.moviejetpack.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private lateinit var binding: ActivityDetailBinding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
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
            viewModel.setSelectedMovie(movie?.id!!)
            viewModel.moviee.observe(this, { data ->
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.moviee.observe(this, { movie ->
            binding.progressBar.visibility = View.GONE
            binding.movie = movie
            val state = movie.isFavorite
            setFavoriteState(state)
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            viewModel.setMovieFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        Log.d("STATE", "this movie state: $state")
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }
}