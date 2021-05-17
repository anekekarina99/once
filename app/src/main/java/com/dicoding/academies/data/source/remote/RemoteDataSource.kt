package com.dicoding.academies.data.source.remote

import android.util.Log
import com.dicoding.academies.api.RetrofitClient
import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.DetailTvEntity
import com.dicoding.academies.data.source.local.entity.MovieEntity
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.data.source.remote.response.MovieResponse
import com.dicoding.academies.data.source.remote.response.TvResponse
import com.dicoding.academies.utils.Constant.Companion.API_KEY
import com.dicoding.academies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {


    companion object {

        private const val TAG = "This Remote Data Source"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource()
                }
    }

    fun getMoviePopular(callback: LoadMovie) {
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService()
                .getMoviePopular(API_KEY, 1)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                        response.body()?.results?.let { callback.onLoadMovie(it) }
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })
    }

    fun getTvPopular(callback: LoadTv) {
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService()
                .getTvList(API_KEY, 1)
                .enqueue(object : Callback<TvResponse> {
                    override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                        response.body()?.results?.let { callback.onLoadTv(it) }
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })
    }

    fun getMovieDetails(callback: LoadMovieDetailCallback, id: Int){
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService()
                .getMovieDetails(id, API_KEY)
                .enqueue(object : Callback<DetailMovieEntity>{
                    override fun onResponse(call: Call<DetailMovieEntity>, response: Response<DetailMovieEntity>) {
                        response.body()?.let { callback.onLoadDetailMovie(it) }
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<DetailMovieEntity>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })
    }

    fun getTvDetails(callback: LoadTvDetailCallback, id: Int){
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService()
                .getTvDetails(id, API_KEY)
                .enqueue(object : Callback<DetailTvEntity>{
                    override fun onResponse(call: Call<DetailTvEntity>, response: Response<DetailTvEntity>) {
                        response.body()?.let { callback.onLoadDetailTv(it) }
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<DetailTvEntity>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })
    }


    interface LoadTvDetailCallback {
        fun onLoadDetailTv(response: DetailTvEntity)
    }

    interface LoadMovieDetailCallback {
        fun onLoadDetailMovie(response: DetailMovieEntity)
    }

    interface LoadMovie {
        fun onLoadMovie(response: ArrayList<MovieEntity>)
    }

    interface LoadTv {
        fun onLoadTv(response: ArrayList<TvEntity>)
    }
}

