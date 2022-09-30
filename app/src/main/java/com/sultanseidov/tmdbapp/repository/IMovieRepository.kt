package com.sultanseidov.tmdbapp.repository

import androidx.lifecycle.LiveData
import com.sultanseidov.tmdbapp.model.base.MovieModel
import com.sultanseidov.tmdbapp.model.base.Resource
import com.sultanseidov.tmdbapp.model.responceModel.ResponsePopularMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseTopRatedMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseUpcomingMovieModel

interface IMovieRepository {

    suspend fun insertMovie(movieModel: MovieModel)

    suspend fun deleteMovie(movieModel: MovieModel)

    fun getMovies(): LiveData<List<MovieModel>>

    suspend fun getUpcomingMovies(): Resource<ResponseUpcomingMovieModel>

    suspend fun getTopRatedMovies(): Resource<ResponseTopRatedMovieModel>

    suspend fun getPopularMovies(): Resource<ResponsePopularMovieModel>

}