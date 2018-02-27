package com.example.marci.seriesreminder.ui.series_overview.viewmodel

/**
 * Created by marci on 2018-02-15.
 */
data class SerieViewModel(
    val id: Int,
    val title: String,
    val originCountry: List<String>?,
    val voteCount: Int,
    val originalLanguage: String,
    val voteAverage: Double,
    val overview: String,
    val photoUrl: String,
    var isSubscribed: Boolean = false
)