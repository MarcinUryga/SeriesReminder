package com.example.marci.seriesreminder.ui.series_watchlist.viewmodel

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.utils.dateTimeToString
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

  fun getNextEpisodeDateInMilis(): Long? {
    return try {
      this.episodes.first {
        isAirDateBiggerOrEqualToday(it)
      }.airDate.let { it!! }
          .stringToJodaTime().millis
    } catch (e: NoSuchElementException) {
      null
    }
  }

  private fun isAirDateBiggerOrEqualToday(it: Episode) =
      it.airDate.let { it!! }.stringToJodaTime() >= DateTime.now()


  fun getNextEpisodeDateString(): String {
    val nextEpisodeDateMilis = getNextEpisodeDateInMilis()
    if (nextEpisodeDateMilis == null) {
      return "Can't find next episode date"
    } else {
      return when (Days.daysBetween(DateTime(getNextEpisodeDateInMilis()).toLocalDate(), DateTime.now().toLocalDate())) {
        Days.ZERO -> return "today"
        Days.ONE -> return "tomorrow"
        Days.TWO -> return "after tomorrow"
        else -> DateTime(getNextEpisodeDateInMilis()).dateTimeToString()
      }
    }
  }
}
