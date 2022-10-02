package com.sultanseidov.tmdbapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel
import com.sultanseidov.tmdbapp.data.entities.tvshow.TvShowModel

@Database(entities = [MovieModel::class, TvShowModel::class], exportSchema = false, version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}