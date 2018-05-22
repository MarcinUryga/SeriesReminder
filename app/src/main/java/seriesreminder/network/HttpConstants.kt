package seriesreminder.network

/**
 ** Created by marci on 2018-02-15.
 */
object HttpConstants {

  const val TMDB_URL = "https://api.themoviedb.org/3/"
  const val TMDB_TV_SERIES_ON_THE_AIR = "tv/on_the_air"
  const val TMDB_TV_SERIE_DETAILS = "tv/{tv_id}"
  const val TMDB_TV_SERIE_SEASONS = "$TMDB_TV_SERIE_DETAILS/season/{season_number}"
  const val TMDB_POSTER_PATH = "https://image.tmdb.org/t/p/w500/"
  const val API_KEY = "8a874fa1d6a3ae2e2082c40950a8ce4d"
}