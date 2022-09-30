package com.sultanseidov.tmdbapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultanseidov.tmdbapp.model.base.MovieModel
import com.sultanseidov.tmdbapp.model.base.Resource
import com.sultanseidov.tmdbapp.model.responceModel.ResponsePopularMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseTopRatedMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseUpcomingMovieModel
import com.sultanseidov.tmdbapp.repository.IMovieRepository
import com.sultanseidov.tmdbapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel@Inject constructor(
    private val repository : IMovieRepository
):ViewModel() {

    val moviesListFromDB = repository.getMovies()

    fun deleteMovie(movie: MovieModel) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }

    fun insertMovie(movie: MovieModel) = viewModelScope.launch {
        repository.insertMovie(movie)
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


}