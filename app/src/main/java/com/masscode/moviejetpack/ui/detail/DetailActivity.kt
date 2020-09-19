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
import com.masscode.moviejetpack.databinding.ActivityDetailBinding
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movie_id"
        const val EXTRA_TV = "tv_id"
        const val EXTRA_TYPE = "type"
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private var menu: Menu? = null

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.movieDetailToolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        type = intent.getStringExtra(EXTRA_TYPE).toString()
        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
        val tvId = intent.getIntExtra(EXTRA_TV, 0)

        if (type == "movie") {
            viewModel.setSelectedMovie(movieId)
            viewModel.movie.observe(this, { data ->
                binding.progressBar.visibility = View.GONE
                binding.movie = data
            })
        } else {
            viewModel.setSelectedTvId(tvId)
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
        if (type == "movie") {
            viewModel.movie.observe(this, { movie ->
                binding.progressBar.visibility = View.GONE
                binding.movie = movie
                val state = movie.isFavorite
                setFavoriteState(state)
            })
        } else {
            viewModel.tvShow.observe(this, { tv ->
                binding.progressBar.visibility = View.GONE
                binding.tvShow = tv
                val state = tv.isFavorite
                setFavoriteState(state)
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            if (type == "movie")
                viewModel.setMovieFavorite()
            else
                viewModel.setTvShowFavorite()
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