package com.sultanseidov.tmdbapp.model.responceModel

import com.sultanseidov.tmdbapp.model.base.TvShowModel

data class ResponsePopularTvShowModel(
    val page: Int,
    val results: List<TvShowModel>,
    val total_pages: Int,
    val total_results: Int
)