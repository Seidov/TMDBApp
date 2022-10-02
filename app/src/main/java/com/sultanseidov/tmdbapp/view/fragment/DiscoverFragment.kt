package com.sultanseidov.tmdbapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sultanseidov.tmdbapp.R
import com.sultanseidov.tmdbapp.data.entities.base.Status
import com.sultanseidov.tmdbapp.databinding.FragmentDiscoverBinding
import com.sultanseidov.tmdbapp.view.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DiscoverFragment : Fragment(R.layout.fragment_discover) {

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
            viewModel.tvShowsListFromDB.observe(viewLifecycleOwner) { tvShowList ->
                if (!tvShowList.isNullOrEmpty()) {
                    viewModel.deleteTvShow(tvShowList[0])
                }
            }
        }
        binding.txvGetMovies.setOnClickListener {
            viewModel.tvShowsListFromDB.observe(viewLifecycleOwner) { tvShowModels ->
                if (!tvShowModels.isNullOrEmpty()) {
                    Log.i(
                        "DiscoverFragment",
                        "list size" + tvShowModels.size + " movie name " + tvShowModels[0].name
                    )
                }
            }
        }

        viewModel.fetchTopRatedTvShows()
        viewModel.fetchPopularTvShows()
        //viewModel.fetchUpcomingMovies()
        //viewModel.fetchPopularMovies()
        //viewModel.fetchTopRatedMovies()

        subscribeToObservers()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToObservers() {
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
        viewModel.popularMoviesList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.popularMoviesList", "SUCCESS")
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.popularMoviesList", "ERROR")
                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.popularMoviesList", "LOADING")
                }
            }
        }
        viewModel.topRatedMoviesList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.topRatedMoviesList", "SUCCESS")
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.topRatedMoviesList", "ERROR")

                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.topRatedMoviesList", "LOADING")

                }
            }
        }

        viewModel.popularTvShowsList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let { list ->
                        Log.e("viewModel.popularTvShowsList", "SUCCESS")

                        if (!list.isNullOrEmpty()) {
                            viewModel.insertTvShow(list[0])
                        }
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.popularTvShowsList", "ERROR")
                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.popularTvShowsList", "LOADING")
                }
            }
        }
        viewModel.topRatedTvShowsList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.results?.let {
                        Log.e("viewModel.topRatedTvShowsList", "SUCCESS")
                    }
                }

                Status.ERROR -> {
                    result.message?.let {
                        Log.e("viewModel.topRatedTvShowsList", "ERROR")
                    }
                }

                Status.LOADING -> {
                    Log.e("viewModel.topRatedTvShowsList", "LOADING")
                }
            }
        }
    }

}