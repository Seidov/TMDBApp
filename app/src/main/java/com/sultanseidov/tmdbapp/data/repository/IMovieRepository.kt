package com.sultanseidov.tmdbapp.data.repository

import androidx.lifecycle.LiveData
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel
import com.sultanseidov.tmdbapp.data.entities.base.Resource
import com.sultanseidov.tmdbapp.data.entities.responceModel.ResponsePopularMovieModel
import com.sultanseidov.tmdbapp.data.entities.responceModel.ResponseTopRatedMovieModel
import com.sultanseidov.tmdbapp.data.entities.responceModel.ResponseUpcomingMovieModel

interface IMovieRepository {

    suspend fun insertMovie(movieModel: MovieModel)

    suspend fun deleteMovie(movieModel: MovieModel)

    fun getMovies(): LiveData<List<MovieModel>>

    suspend fun getUpcomingMovies(): Resource<ResponseUpcomingMovieModel>

    suspend fun getTopRatedMovies(): Resource<ResponseTopRatedMovieModel>

    suspend fun getPopularMovies(): Resource<ResponsePopularMovieModel>

}