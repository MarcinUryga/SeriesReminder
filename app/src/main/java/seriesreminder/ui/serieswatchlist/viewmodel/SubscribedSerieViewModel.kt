package seriesreminder.ui.serieswatchlist.viewmodel

import org.joda.time.DateTime
import org.joda.time.Days
import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.utils.dateTimeToShortDate
import seriesreminder.utils.dateTimeToString
import seriesreminder.utils.stringToJodaTime

/**
 ** Created by marci on 2018-02-23.
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
    val episodes: List<Episode>,
    val isSubscribed: Boolean
) {

  fun getNextEpisodeDateInMillis(): Long? {
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
      it.airDate.let { it!! }.stringToJodaTime() >= DateTime.now().dateTimeToShortDate()

  fun getNextEpisodeDateString(): String {
    val nextEpisodeDateMillis = getNextEpisodeDateInMillis()
    return if (nextEpisodeDateMillis == null) {
      "Can't find next episode date"
    } else {
      when (Days.daysBetween(DateTime(getNextEpisodeDateInMillis()).toLocalDate(), DateTime.now().toLocalDate())) {
        Days.ZERO -> return "today"
        Days.ONE -> return "tomorrow"
        Days.TWO -> return "after tomorrow"
        else -> DateTime(getNextEpisodeDateInMillis()).dateTimeToString()
      }
    }
  }
}
