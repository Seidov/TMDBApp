package com.sultanseidov.tmdbapp.repository.localds

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sultanseidov.tmdbapp.model.base.MovieModel

@Database(entities = [MovieModel::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao():MovieDao
}