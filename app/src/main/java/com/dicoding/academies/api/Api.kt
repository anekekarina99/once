package com.dicoding.academies.api

import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.DetailTvEntity
import com.dicoding.academies.data.source.remote.response.DetailMovieResponse
import com.dicoding.academies.data.source.remote.response.DetailTvResponse
import com.dicoding.academies.data.source.remote.response.MovieResponse
import com.dicoding.academies.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("discover/movie")
    fun getMoviePopular( @Query("api_key") apiKey: String, @Query("page") page : Int) : Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int,  @Query("api_key") apiKey: String): Call<DetailMovieEntity>

    @GET("tv/{tv_id}")
    fun getTvDetails(@Path("tv_id") id : Int,  @Query("api_key") apiKey: String ) : Call<DetailTvEntity>

    @GET("discover/tv")
    fun getTvList( @Query("api_key") apiKey: String, @Query("page") page : Int) : Call<TvResponse>

}