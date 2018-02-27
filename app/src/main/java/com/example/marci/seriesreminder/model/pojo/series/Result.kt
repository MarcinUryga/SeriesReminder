package com.example.marci.seriesreminder.model.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    var id: Int? = null,
    var name: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    @SerializedName("original_name")
    @Expose
    var originalName: String? = null,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null,
    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
)