package com.sultanseidov.tmdbapp.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel
import com.sultanseidov.tmdbapp.data.entities.base.Resource
import com.sultanseidov.tmdbapp.data.entities.responceModel.*
import com.sultanseidov.tmdbapp.data.entities.tvshow.TvShowModel
import com.sultanseidov.tmdbapp.data.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel@Inject constructor(
    private val repository : IMovieRepository
):ViewModel() {

    val moviesListFromDB = repository.getMovies()
    val tvShowsListFromDB = repository.getTvShows()

    fun deleteMovie(movie: MovieModel) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }
    fun insertMovie(movie: MovieModel) = viewModelScope.launch {
        repository.insertMovie(movie)
    }

    fun deleteTvShow(tvShowModel: TvShowModel) = viewModelScope.launch {
        repository.deleteTvShow(tvShowModel)
    }
    fun insertTvShow(tvShowModel: TvShowModel) = viewModelScope.launch {
        repository.insertTvShow(tvShowModel)
    }

    private val upcomingMovies = MutableLiveData<Resource<ResponseUpcomingMovieModel>>()
    val upcomingMoviesList : LiveData<Resource<ResponseUpcomingMovieModel>>
        get() = upcomingMovies

    private val topRatedMovies = MutableLiveData<Resource<ResponseTopRatedMovieModel>>()
    val topRatedMoviesList : LiveData<Resource<ResponseTopRatedMovieModel>>
        get() = topRatedMovies

    private val popularMovies = MutableLiveData<Resource<ResponsePopularMovieModel>>()
    val popularMoviesList : LiveData<Resource<ResponsePopularMovieModel>>
        get() = popularMovies

    private val topRatedTvShows = MutableLiveData<Resource<ResponseTopRatedTvShowModel>>()
    val topRatedTvShowsList : LiveData<Resource<ResponseTopRatedTvShowModel>>
        get() = topRatedTvShows

    private val popularTvShows = MutableLiveData<Resource<ResponsePopularTvShowModel>>()
    val popularTvShowsList : LiveData<Resource<ResponsePopularTvShowModel>>
        get() = popularTvShows

    fun fetchUpcomingMovies() {
        upcomingMovies.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getUpcomingMovies()
            upcomingMovies.value = response
        }
    }
    fun fetchTopRatedMovies() {
        topRatedMovies.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getTopRatedMovies()
            topRatedMovies.value = response
        }
    }
    fun fetchPopularMovies() {
        popularMovies.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getPopularMovies()
            popularMovies.value = response
        }
    }

    fun fetchTopRatedTvShows() {
        topRatedTvShows.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getTopRatedTvShows()
            topRatedTvShows.value = response
        }
    }
    fun fetchPopularTvShows() {
        popularTvShows.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getPopularTvShows()
            popularTvShows.value = response
        }
    }

}