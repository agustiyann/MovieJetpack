package com.masscode.moviejetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
abstract class FavoriteDb : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDb? = null

        fun getInstance(context: Context): FavoriteDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDb::class.java,
                    "Favorite.db"
                ).build()
            }
    }

}