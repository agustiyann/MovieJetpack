package com.masscode.moviejetpack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.ui.favorite.FavoriteFragment
import com.masscode.moviejetpack.ui.movie.MovieFragment
import com.masscode.moviejetpack.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.movies -> {
                    fragment = MovieFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.container_layout,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tv_show -> {
                    fragment = TvShowFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.container_layout,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    fragment = FavoriteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.container_layout,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        supportActionBar?.elevation = 0f

        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            bottom_nav.selectedItemId = R.id.movies
        }
    }
}