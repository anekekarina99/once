package com.dicoding.academies.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
        @SerializedName("id")
        var id: Int,
        @SerializedName("poster_path")
        var posterPath : String,
        @SerializedName("original_title")
        var originalTitle: String,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("overview")
        var overview: String,
)
