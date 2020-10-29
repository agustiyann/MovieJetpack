package com.masscode.moviejetpack.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.databinding.ActivityDetailTvShowBinding
import com.masscode.moviejetpack.ui.MainActivity
import com.masscode.moviejetpack.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_ORIGIN_TV = "extra_origin_tv"
    }

    private lateinit var tvShowViewModel: DetailTvShowViewModel
    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var mTvShow: TvShow
    private var statusFavorite: Boolean = false
    private lateinit var originScreen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_show)
        mTvShow = intent.getParcelableExtra(EXTRA_TV)!!
        originScreen = intent.getStringExtra(EXTRA_ORIGIN_TV)!!
        statusFavorite = mTvShow.isFavorite
        setStatusFavorite(statusFavorite)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        tvShowViewModel = ViewModelProvider(this, viewModelFactory).get(DetailTvShowViewModel::class.java)

        with(binding) {
            activity = this@DetailTvShowActivity
            lifecycleOwner = this@DetailTvShowActivity
            tvShow = mTvShow
            fab.setOnClickListener { fabListener() }
        }
    }

    private fun fabListener() {
        statusFavorite = !statusFavorite
        tvShowViewModel.setFavoriteTvShow(mTvShow, statusFavorite)
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
            "tv_show" -> {
                intent.putExtra(MainActivity.EXTRA_SCREEN, "tv_show")
                startActivity(intent)
                finish()
            }
            "favorite" -> {
                intent.putExtra(MainActivity.EXTRA_SCREEN, "favorite")
                startActivity(intent)
                finish()
            }
        }
    }
}