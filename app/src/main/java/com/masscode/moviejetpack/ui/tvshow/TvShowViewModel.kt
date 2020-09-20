package com.masscode.moviejetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TvShowViewModel(private val repository: Repository) : ViewModel() {

    fun getTvShowList(): LiveData<PagedList<TvShow>> = repository.getTvShowsLocal()

    private val vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        coroutineScope.launch {
            try {
                repository.getTvShow()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}