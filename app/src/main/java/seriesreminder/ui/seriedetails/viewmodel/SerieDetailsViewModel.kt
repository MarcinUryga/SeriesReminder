package seriesreminder.ui.seriedetails.viewmodel

import seriesreminder.model.pojo.seasons.Episode

/**
 ** Created by marci on 2018-02-22.
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
    val backdropPath: String = "",
    val seasonNumber: Int = 0,
    val episodes: List<Episode> = emptyList()
)