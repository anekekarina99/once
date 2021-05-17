package com.dicoding.academies.data


import androidx.lifecycle.LiveData
import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.DetailTvEntity
import com.dicoding.academies.data.source.local.entity.MovieEntity
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.data.source.remote.response.DetailMovieResponse
import com.dicoding.academies.data.source.remote.response.DetailTvResponse

interface AcademyDataSource {

    fun getMoviePopular(): LiveData<ArrayList<MovieEntity>>

    fun getMovieDetail(id : Int): LiveData<DetailMovieEntity>

    fun getTvPopular(): LiveData<ArrayList<TvEntity>>

    fun getTvDetail(id : Int): LiveData<DetailTvEntity>
}