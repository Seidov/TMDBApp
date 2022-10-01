package com.sultanseidov.tmdbapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel

@Database(entities = [MovieModel::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}