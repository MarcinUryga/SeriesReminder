package com.example.marci.seriesreminder.repositories

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.model.realm.EpisodeRealm
import com.example.marci.seriesreminder.model.realm.SerieRealm
import com.example.marci.seriesreminder.model.realm.SeriesPageRealm
import com.example.marci.seriesreminder.network.HttpConstants
import com.example.marci.seriesreminder.network.SerieDetailsAPI
import com.example.marci.seriesreminder.network.SeriesOnTheAirAPI
import com.example.marci.seriesreminder.utils.toRealmList
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.realm.RealmList
import javax.inject.Inject
import com.example.marci.seriesreminder.model.pojo.details.Example as SerieDetails
import com.example.marci.seriesreminder.model.pojo.seasons.Example as SeasonDetails
import com.example.marci.seriesreminder.model.pojo.series.Example as SeriesOnTheAirResult

/**
 * Created by marci on 2018-02-20.
 */
class SeriesRepository @Inject constructor(
    private val seriesOnTheAirAPI: SeriesOnTheAirAPI,
    private val serieDetailsAPI: SerieDetailsAPI,
    realmManager: RealmManager
) : RealmRepository(realmManager) {

  fun saveSeriesPage(page: Int, updatedSeries: List<Int>): Completable {
    return getSeriesOnTheAirResult(page).doOnSuccess { seriesPage ->
      copySeriesFromPageToRealm(seriesPage.apply {
        results = results?.filter { serie ->
          updatedSeries.all { it != serie.id }
        }
      })
    }.toCompletable()
  }

  fun saveSerieDetails(serieId: Int): Completable {
    return zipSerieDetails(serieId).doOnSuccess { details ->
      copyOrUpdate(details)
    }.toCompletable()
  }

  fun zipSerieDetails(serieId: Int): Single<SerieRealm> {
    return Single.zip(
        getSerieDetails(serieId),
        getSerieEpisodes(serieId),
        BiFunction { serieDetails, seasonEpisodes ->
          transformToSerieDetailsRealm(serieDetails, seasonEpisodes)
        }
    )
  }

  private fun getSeriesOnTheAirResult(page: Int = 1): Single<SeriesOnTheAirResult> {
    return seriesOnTheAirAPI.getResult(page)
  }

  private fun getSerieDetails(serieId: Int): Single<SerieDetails> {
    return serieDetailsAPI.getDetails(serieId)
  }

  private fun getSerieEpisodes(serieId: Int): Single<SeasonDetails> {
    return getSerieDetails(serieId).flatMap { details: SerieDetails ->
      serieDetailsAPI.getSeason(serieId, details.seasons?.let {
        var lastSeason: Int = it.last().seasonNumber.let { it!! }
        when {
          lastSeason < it.size && it[lastSeason].episodeCount == 0 -> return@let lastSeason - 1
          ++lastSeason != details.numberOfSeasons -> return@let it.last().seasonNumber.let { it!! }
          else -> return@let details.numberOfSeasons
        }
      }.let { it!! })
    }
  }

  private fun copySeriesFromPageToRealm(seriesPage: com.example.marci.seriesreminder.model.pojo.series.Example) {
    /*copyOrUpdateWithQuery(
        realmObjects = transformSeriesToRealmObjects(results, page),
        realmClass = SerieRealm::class,
        query = { `in`("id", results?.map { it.id }?.toTypedArray()).findAll().size },
        queryResult = 0
    )*/
    copyOrUpdate(transformSeriesToRealmObjects(seriesPage))
  }

  private fun transformSeriesToRealmObjects(seriesPage: com.example.marci.seriesreminder.model.pojo.series.Example): SeriesPageRealm {
    return SeriesPageRealm().apply {
      page = seriesPage.page.let { it!! }
      totalPages = seriesPage.totalPages.let { it!! }
      totalResults = seriesPage.totalResults.let { it!! }
      series = seriesPage.results?.map { serie ->
        SerieRealm().apply {
          id = serie.id.let { it!! }
          name = serie.name.let { it!! }
          originCountry = serie.originCountry?.toRealmList()
          posterPath = serie.posterPath ?: HttpConstants.MOVIE_PLACEHOLDER
          overview = serie.overview.let { it!! }
          voteAverage = serie.voteAverage.let { it!! }
          originalLanguage = serie.originalLanguage.let { it!! }
          voteCount = serie.voteCount.let { it!! }
        }
      }?.toRealmList()
    }
  }

  private fun transformToSerieDetailsRealm(serieDetails: com.example.marci.seriesreminder.model.pojo.details.Example, seasonEpisodes: com.example.marci.seriesreminder.model.pojo.seasons.Example): SerieRealm {
    return SerieRealm().apply {
      id = serieDetails.id.let { it!! }
      voteCount = serieDetails.voteCount ?: 0
      originalLanguage = serieDetails.originalLanguage ?: ""
      voteAverage = serieDetails.voteAverage ?: 0.0
      overview = serieDetails.overview ?: ""
      posterPath = HttpConstants.TMDP_POSTER_PATH + serieDetails.posterPath
      originCountry = serieDetails.originCountry?.toRealmList()
      backdropPath = HttpConstants.TMDP_POSTER_PATH + serieDetails.backdropPath
      name = serieDetails.name.let { it!! }
      episodes = transformEpisodesToRealm(seasonEpisodes.episodes.let { it!! })
      numberOfSeasons = serieDetails.seasons?.let {
        var lastSeason: Int = it.last().seasonNumber.let { it!! }
        if (++lastSeason != serieDetails.numberOfSeasons) {
          return@let it.last().seasonNumber.let { it!! }
        } else {
          return@let serieDetails.numberOfSeasons
        }
      }.let { it!! }
    }
  }

  private fun transformEpisodesToRealm(episodes: List<Episode>): RealmList<EpisodeRealm> {
    return episodes.map { episode ->
      EpisodeRealm().apply {
        id = episode.id.let { it!! }
        name = episode.name.let { it!! }
        overview = episode.overview.let { it!! }
        airDate = episode.airDate ?: ""
        episodeNumber = episode.episodeNumber.let { it!! }
        seasonNumber = episode.seasonNumber.let { it!! }
        stillPath = HttpConstants.TMDP_POSTER_PATH + episode.stillPath.toString()
        voteAverage = episode.voteAverage.let { it!! }
        voteCount = episode.voteCount.let { it!! }
      }
    }.toRealmList()
  }
}
