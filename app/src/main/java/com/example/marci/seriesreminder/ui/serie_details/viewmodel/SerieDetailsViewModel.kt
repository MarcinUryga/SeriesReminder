package com.example.marci.seriesreminder.ui.serie_details.viewmodel

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import io.realm.RealmList

/**
 * Created by marci on 2018-02-22.
 */
data class SerieDetailsViewModel(

    val id: Int = 0,
    val name: String = "",
    val originCountry: List<String>? = null,
    val voteCount: Int = 0,
    val originalLanguage: String = "",
    val voteAverage: Double = 0.0,
    val overview: String = "",
    val posterPath: String = "",
    val serieFromPage: Int = 0,
    val seasonNumber: Int = 0,
    val episodes: List<Episode>? = null
)