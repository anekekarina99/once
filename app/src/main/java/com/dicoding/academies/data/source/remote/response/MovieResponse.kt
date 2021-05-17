package com.dicoding.academies.data.source.remote.response

import com.dicoding.academies.data.source.local.entity.MovieEntity

data class MovieResponse(
        val results: ArrayList<MovieEntity>
)