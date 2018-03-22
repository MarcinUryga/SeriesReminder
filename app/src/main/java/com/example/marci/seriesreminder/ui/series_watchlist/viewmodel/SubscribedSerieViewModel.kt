package com.example.marci.seriesreminder.ui.series_watchlist.viewmodel

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.utils.stringToJodaTime
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

  fun getNexEpisodeDate(): String = this.episodes.first { it.airDate.let { it!! }.stringToJodaTime() >= DateTime.now() }.airDate.let { it!! }


  fun getNextEpisodeDateString(): String {
    return when (Days.daysBetween(getNexEpisodeDate().stringToJodaTime().toLocalDate(), DateTime.now().toLocalDate())) {
      Days.ZERO -> return "today"
      Days.ONE -> return "tomorrow"
      Days.TWO -> return "after tomorrow"
      else -> getNexEpisodeDate()
    }
  }
}
