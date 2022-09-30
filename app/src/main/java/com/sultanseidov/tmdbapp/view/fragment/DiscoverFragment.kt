package com.sultanseidov.tmdbapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.sultanseidov.tmdbapp.R
import com.sultanseidov.tmdbapp.model.base.Status
import com.sultanseidov.tmdbapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DiscoverFragment:Fragment(R.layout.fragment_discover) {

    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUpcomingMovies()
        viewModel.fetchPopularMovies()
        viewModel.fetchTopRatedMovies()
        subscribeToObservers()
    }

    fun subscribeToObservers(){
        viewModel.upcomingMoviesList.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.upcomingMoviesList", "SUCCESS")
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.upcomingMoviesList", "ERROR")

                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.upcomingMoviesList", "LOADING")

                }
            }

        }
        viewModel.popularMoviesList.observe(viewLifecycleOwner, Observer { result->

            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.popularMoviesList","SUCCESS")
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.popularMoviesList","ERROR")

                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.popularMoviesList","LOADING")

                }
            }

        })
        viewModel.topRatedMoviesList.observe(viewLifecycleOwner, Observer { result->

            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.topRatedMoviesList","SUCCESS")
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.topRatedMoviesList","ERROR")

                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.topRatedMoviesList","LOADING")

                }
            }

        })

    }

}