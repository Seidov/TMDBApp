package com.sultanseidov.tmdbapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.sultanseidov.tmdbapp.R
import com.sultanseidov.tmdbapp.data.entities.base.Status
import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel
import com.sultanseidov.tmdbapp.databinding.FragmentDiscoverBinding
import com.sultanseidov.tmdbapp.view.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DiscoverFragment:Fragment(R.layout.fragment_discover) {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txvDelete.setOnClickListener {
            viewModel.moviesListFromDB.observe(viewLifecycleOwner){ movieList ->
                if (!movieList.isNullOrEmpty()){
                    viewModel.deleteMovie(movieList[0])
                }

            }
        }


        binding.txvGetMovies.setOnClickListener {
            viewModel.moviesListFromDB.observe(viewLifecycleOwner){movieList->
                if (!movieList.isNullOrEmpty()){
                    Log.i("DiscoverFragment","list size"+movieList.size+" movie name "+movieList[0].title)
                }
            }
        }

        viewModel.fetchUpcomingMovies()
        //viewModel.fetchPopularMovies()
        //viewModel.fetchTopRatedMovies()

        subscribeToObservers()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToObservers(){
        viewModel.upcomingMoviesList.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.upcomingMoviesList", "SUCCESS")

                        viewModel.insertMovie(it[0])

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