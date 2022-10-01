package com.sultanseidov.tmdbapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieModel: MovieModel)

    @Delete
    suspend fun deleteMovie(movieModel: MovieModel)

    @Query("SELECT * FROM movies")
    fun observeMovies():LiveData<List<MovieModel>>

}