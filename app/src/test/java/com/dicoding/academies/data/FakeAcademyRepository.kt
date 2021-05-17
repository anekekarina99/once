package com.dicoding.academies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.DetailTvEntity
import com.dicoding.academies.data.source.local.entity.MovieEntity
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.data.source.remote.RemoteDataSource

class FakeAcademyRepository(private val remoteDataSource: RemoteDataSource) : AcademyDataSource {
    private val movieResults = MutableLiveData<ArrayList<MovieEntity>>()
    private val tvResults = MutableLiveData<ArrayList<TvEntity>>()
    private val movieDetail = MutableLiveData<DetailMovieEntity>()
    private val tvDetail = MutableLiveData<DetailTvEntity>()


    override fun getMoviePopular(): LiveData<ArrayList<MovieEntity>> {
        remoteDataSource.getMoviePopular(object : RemoteDataSource.LoadMovie {
            override fun onLoadMovie(response: ArrayList<MovieEntity>) {
                val mPost = ArrayList<MovieEntity>()
                for (res in response) {
                    val m = MovieEntity(
                            id = res.id,
                            posterPath = res.posterPath,
                            originalTitle = res.originalTitle,
                            releaseDate = res.releaseDate,
                            overview = res.overview
                    )
                    mPost.add(m)
                }
                movieResults.postValue(mPost)

            }

        })
        return movieResults
    }


    override fun getMovieDetail(id: Int): LiveData<DetailMovieEntity> {
        remoteDataSource.getMovieDetails(object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onLoadDetailMovie(response: DetailMovieEntity) {
                val mDetail = DetailMovieEntity(
                        id = response.id,
                        posterPath = response.posterPath,
                        originalTitle = response.originalTitle,
                        releaseDate = response.releaseDate,
                        overview = response.overview
                )
                movieDetail.postValue(mDetail)

            }

        }, id)
        return movieDetail
    }

    override fun getTvPopular(): LiveData<ArrayList<TvEntity>> {
        remoteDataSource.getTvPopular(object : RemoteDataSource.LoadTv {
            override fun onLoadTv(response: ArrayList<TvEntity>) {
                val tPost = ArrayList<TvEntity>()
                for (res in response) {
                    val m = TvEntity(
                            id = res.id,
                            posterPath = res.posterPath,
                            originalName = res.originalName,
                            firstAirDate = res.firstAirDate,
                            overview = res.overview
                    )
                    tPost.add(m)
                }
                tvResults.postValue(tPost)

            }

        })
        return tvResults
    }

    override fun getTvDetail(id: Int): LiveData<DetailTvEntity> {
        remoteDataSource.getTvDetails(object : RemoteDataSource.LoadTvDetailCallback {
            override fun onLoadDetailTv(response: DetailTvEntity) {
                val tDetail = DetailTvEntity(
                        id = response.id,
                        posterPath = response.posterPath,
                        originalName = response.originalName,
                        firstAirDate = response.firstAirDate,
                        overview = response.overview
                )
                tvDetail.postValue(tDetail)

            }

        }, id)
        return tvDetail
    }


}

