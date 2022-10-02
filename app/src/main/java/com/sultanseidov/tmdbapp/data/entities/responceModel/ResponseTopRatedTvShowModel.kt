package com.sultanseidov.tmdbapp.data.entities.responceModel

import com.sultanseidov.tmdbapp.data.entities.tvshow.TvShowModel

data class ResponseTopRatedTvShowModel(
    val page: Int,
    val results: List<TvShowModel>,
    val total_pages: Int,
    val total_results: Int
)