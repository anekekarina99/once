package com.dicoding.academies.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class TvEntity(
        @SerializedName("id")
        var id: Int,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("original_name")
        var originalName: String,
        @SerializedName("first_air_date")
        var firstAirDate: String,
        @SerializedName("overview")
        var overview: String
)

