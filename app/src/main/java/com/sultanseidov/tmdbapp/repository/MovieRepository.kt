package com.sultanseidov.tmdbapp.repository

import androidx.lifecycle.LiveData
import com.sultanseidov.tmdbapp.model.base.MovieModel
import com.sultanseidov.tmdbapp.model.base.Resource
import com.sultanseidov.tmdbapp.model.responceModel.ResponsePopularMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseTopRatedMovieModel
import com.sultanseidov.tmdbapp.model.responceModel.ResponseUpcomingMovieModel
import com.sultanseidov.tmdbapp.repository.localds.MovieDao
import com.sultanseidov.tmdbapp.repository.remoteds.ITMDBApi
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val retrofitApi: ITMDBApi
) : IMovieRepository {

    override suspend fun insertMovie(movieModel: MovieModel) {
        movieDao.insertMovie(movieModel)
    }

    override suspend fun deleteMovie(movieModel: MovieModel) {
        movieDao.deleteMovie(movieModel)
    }

    override fun getMovies(): LiveData<List<MovieModel>> {
        return movieDao.observeMovies()
    }

    override suspend fun getUpcomingMovies(): Resource<ResponseUpcomingMovieModel> {
        return try {
            val response = retrofitApi.getUpcomingMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        }
        catch (e:Exception){
            Resource.error("No Data!",null)
        }
    }

    override suspend fun getTopRatedMovies(): Resource<ResponseTopRatedMovieModel> {
        return try {
            val response = retrofitApi.getTopRatedMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        }
        catch (e:Exception){
            Resource.error("No Data!",null)
        }    }

    override suspend fun getPopularMovies(): Resource<ResponsePopularMovieModel> {
        return try {
            val response = retrofitApi.getPopularMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        }
        catch (e:Exception){
            Resource.error("No Data!",null)
        }    }
}