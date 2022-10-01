package com.sultanseidov.tmdbapp.data.entities.responceModel

import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel

data class ResponsePopularMovieModel(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)