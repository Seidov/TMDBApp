package com.sultanseidov.tmdbapp.repository.remoteds

import com.sultanseidov.tmdbapp.model.responceModel.ResponsePopularMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseTopRatedMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseUpcomingMovieModel
import com.sultanseidov.tmdbapp.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITMDBApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key:String=API_KEY,
        @Query("language") language:String="en-US",
        @Query("page") page:String="1"
    ): Response<ResponseUpcomingMovieModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key:String=API_KEY,
        @Query("language") language:String="en-US",
        @Query("page") page:String="1"
    ):Response<ResponseTopRatedMovieModel>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key:String=API_KEY,
        @Query("language") language:String="en-US",
        @Query("page") page:String="1"
    ):Response<ResponsePopularMovieModel>
}