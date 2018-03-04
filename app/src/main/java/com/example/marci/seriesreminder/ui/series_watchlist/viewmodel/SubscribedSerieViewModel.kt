package com.example.marci.seriesreminder.ui.series_watchlist.viewmodel

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import org.joda.time.DateTime
import org.joda.time.Days

/**
 * Created by marci on 2018-02-23.
 */
data class SubscribedSerieViewModel(
    val id: Int,
    val title: String,
    val originCountry: List<String>?,
    val voteCount: Int,
    val originalLanguage: String,
    val voteAverage: Double,
    val overview: String,
    val photoUrl: String,
    val episodes: List<Episode>
) {

  fun getNextEpisodeDate(): String {
    val nextEpisodeDateString = this.episodes.first { airDateToJodaTime(it.airDate.let { it!! }) >= DateTime.now() }.airDate.let { it!! }
    return when (Days.daysBetween(airDateToJodaTime(nextEpisodeDateString).toLocalDate(), DateTime.now().toLocalDate())) {
      Days.ZERO -> return "today"
      Days.ONE -> return "tomorrow"
      Days.TWO -> return "after tomorrow"
      else -> nextEpisodeDateString
    }
  }

  private fun airDateToJodaTime(airDate: String): DateTime {
    val dateFields = airDate.split("-")
    return DateTime(
        dateFields[0].toInt(),
        dateFields[1].toInt(),
        dateFields[2].toInt(),
        23,
        59)
  }
}
