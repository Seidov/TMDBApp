package com.sultanseidov.tmdbapp.data.repository

import androidx.lifecycle.LiveData
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel
import com.sultanseidov.tmdbapp.data.entities.base.Resource
import com.sultanseidov.tmdbapp.data.entities.responceModel.*
import com.sultanseidov.tmdbapp.data.entities.responceModel.ResponseMultiSearchModel
import com.sultanseidov.tmdbapp.data.entities.tvshow.TvShowModel

interface IMovieRepository {

    fun getMovies(): LiveData<List<MovieModel>>

    fun getTvShows(): LiveData<List<TvShowModel>>

    suspend fun insertMovie(movieModel: MovieModel)

    suspend fun deleteMovie(movieModel: MovieModel)

    suspend fun insertTvShow(tvShowModel:TvShowModel)

    suspend fun deleteTvShow(tvShowModel:TvShowModel)

    suspend fun getUpcomingMovies(): Resource<ResponseUpcomingMovieModel>

    suspend fun getTopRatedMovies(): Resource<ResponseTopRatedMovieModel>

    suspend fun getPopularMovies(): Resource<ResponsePopularMovieModel>

    suspend fun getTopRatedTvShows(): Resource<ResponseTopRatedTvShowModel>

    suspend fun getPopularTvShows(): Resource<ResponsePopularTvShowModel>

    suspend fun getMultiSearch(query:String): Resource<ResponseMultiSearchModel>

}