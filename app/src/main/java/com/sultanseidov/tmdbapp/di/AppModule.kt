package com.sultanseidov.tmdbapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.sultanseidov.tmdbapp.repository.IMovieRepository
import com.sultanseidov.tmdbapp.repository.MovieRepository
import com.sultanseidov.tmdbapp.repository.localds.AppDatabase
import com.sultanseidov.tmdbapp.repository.localds.MovieDao
import com.sultanseidov.tmdbapp.repository.remoteds.ITMDBApi
import com.sultanseidov.tmdbapp.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context)=Room.databaseBuilder(
        context,AppDatabase::class.java,"TMDBApp")
        .build()


    @Singleton
    @Provides
    fun injectDao(database: AppDatabase)=database.movieDao()


    @Singleton
    @Provides
    fun injectRetrofitApi():ITMDBApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ITMDBApi::class.java)
    }


    @Singleton
    @Provides
    fun injectNormalRepo(dao : MovieDao, api: ITMDBApi) = MovieRepository(dao,api) as IMovieRepository


}