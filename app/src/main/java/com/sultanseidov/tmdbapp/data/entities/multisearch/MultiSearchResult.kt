package com.sultanseidov.tmdbapp.data.entities.multisearch

import com.sultanseidov.tmdbapp.data.entities.movie.MovieModel
import com.sultanseidov.tmdbapp.data.entities.tvshow.TvShowModel

data class MultiSearchResult(val moviesList: List<MovieModel>?=null,val tvShowsList: List<TvShowModel>?=null) {
}