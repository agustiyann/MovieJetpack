package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.di.Injection
import com.masscode.moviejetpack.ui.detail.DetailViewModel
import com.masscode.moviejetpack.ui.tvshow.TvShowViewModel

class MovieViewModelFactory private constructor(private val mMovieRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MovieViewModelFactory? = null

        // kode synchronized untuk membuat semua thread tersinkronisasi. Dengan cara ini,
        // hanya satu thread yang boleh menjalankan fungsi yang sama di waktu yang sama.
        fun getInstance(): MovieViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}