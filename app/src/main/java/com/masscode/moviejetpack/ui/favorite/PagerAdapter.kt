package com.masscode.moviejetpack.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.masscode.moviejetpack.ui.favorite.movie.MovieFavoriteFragment
import com.masscode.moviejetpack.ui.favorite.tvshow.TvShowFavoriteFragment

class PagerAdapter(
    fm: FragmentManager,
    var totalTabs: Int
) : FragmentStatePagerAdapter(fm, totalTabs) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MovieFavoriteFragment()
            }
            1 -> {
                TvShowFavoriteFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}