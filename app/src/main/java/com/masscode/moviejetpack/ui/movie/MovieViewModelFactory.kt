package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.data.MovieRespository
import com.masscode.moviejetpack.di.Injection

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory private constructor(private val mMovieRepository: MovieRespository): ViewModelProvider.NewInstanceFactory() {

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

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(mMovieRepository) as T
    }
}