package com.masscode.moviejetpack.ui.tvshow

import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.TvShow
import com.masscode.moviejetpack.utils.DummyData

class TvShowViewModel : ViewModel() {

    fun getTvShowList(): List<TvShow> = DummyData.generateTvShowList()

}