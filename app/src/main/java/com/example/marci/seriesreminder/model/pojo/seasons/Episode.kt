package com.example.marci.seriesreminder.model.pojo.seasons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Episode(
    var id: Int? = null,
    var name: String? = null,
    var crew: List<Crew>? = null,
    var overview: String? = null,
    @SerializedName("air_date")
    @Expose
    var airDate: String? = null,
    @SerializedName("episode_number")
    @Expose
    var episodeNumber: Int? = null,
    @SerializedName("guest_stars")
    @Expose
    var guestStars: List<Any>? = null,
    @SerializedName("production_code")
    @Expose
    var productionCode: Any? = null,
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null,
    @SerializedName("still_path")
    @Expose
    var stillPath: Any? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
)
