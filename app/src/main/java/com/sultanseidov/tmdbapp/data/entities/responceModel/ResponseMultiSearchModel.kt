package com.sultanseidov.tmdbapp.data.entities.responceModel

import com.sultanseidov.tmdbapp.data.entities.multisearch.Result

data class ResponseMultiSearchModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)