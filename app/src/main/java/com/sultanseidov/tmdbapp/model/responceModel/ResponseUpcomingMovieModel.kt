package com.sultanseidov.tmdbapp.model.responceModel

import com.sultanseidov.tmdbapp.model.base.MovieModel
import com.sultanseidov.tmdbapp.model.movie.Dates

data class ResponseUpcomingMovieModel(
    val dates: Dates,
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)