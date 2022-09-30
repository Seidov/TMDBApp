package com.sultanseidov.tmdbapp.model.responceModel

import com.sultanseidov.tmdbapp.model.base.MovieModel

data class ResponsePopularMovieModel(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)