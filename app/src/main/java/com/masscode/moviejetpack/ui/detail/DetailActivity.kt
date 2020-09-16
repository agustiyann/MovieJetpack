package com.masscode.moviejetpack.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.databinding.ActivityDetailBinding
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ID = "id"
        const val TYPE = "type"
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

        val viewModelFactory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val extras = intent.extras

        if (extras != null) {
            val id = extras.getInt(ID)
            val type = extras.getString(TYPE)
            Log.d("type", "type: $type")

            if (type == "movie") {
                viewModel.setSelectedMovie(id)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getDetailMovie().observe(this, { movie ->
                    binding.progressBar.visibility = View.GONE
                    binding.movie = movie
                })
            } else {
                viewModel.setSelectedTvShow(id)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getDetailTvShow().observe(this, { tvShow ->
                    binding.progressBar.visibility = View.GONE
                    binding.tvShow = tvShow
                })
            }
        }

        binding.viewModel = viewModel
    }
}