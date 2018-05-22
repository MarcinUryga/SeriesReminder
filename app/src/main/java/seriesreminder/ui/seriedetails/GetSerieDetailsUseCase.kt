package seriesreminder.ui.seriedetails

import io.reactivex.Single
import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.model.realm.SerieRealm
import seriesreminder.network.HttpConstants
import seriesreminder.repositories.SeriesRepository
import seriesreminder.ui.seriedetails.viewmodel.SerieDetailsViewModel
import seriesreminder.utils.NetworkConnection
import javax.inject.Inject

/**
 ** Created by marci on 2018-02-22.
 */
class GetSerieDetailsUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val networkConnection: NetworkConnection
) {

  fun updateAndGet(serieId: Int): Single<SerieDetailsViewModel> {
    return seriesRepository.let {
      if (networkConnection.isOnline()) {
        it.saveSerieDetails(serieId).andThen(getSerieDetailsFromRealm(serieId))
      } else {
        getSerieDetailsFromRealm(serieId)
      }
    }
  }

  private fun getSerieDetailsFromRealm(serieId: Int): Single<SerieDetailsViewModel> {
    return Single.fromCallable {
      seriesRepository.get(SerieRealm::class) {
        val serie = equalTo("id", serieId).findFirst().let { it!! }
        SerieDetailsViewModel(
            id = serie.id,
            name = serie.name,
            originCountry = serie.originCountry?.toList(),
            voteCount = serie.voteCount,
            originalLanguage = serie.originalLanguage,
            voteAverage = serie.voteAverage,
            overview = serie.overview,
            posterPath = serie.posterPath,
            backdropPath = getPosterUrl(serie),
            seasonNumber = serie.numberOfSeasons,
            episodes = serie.episodes.map { episodeRealm ->
              Episode(
                  id = episodeRealm.id,
                  name = episodeRealm.name,
                  overview = episodeRealm.overview,
                  airDate = episodeRealm.airDate,
                  episodeNumber = episodeRealm.episodeNumber,
                  seasonNumber = episodeRealm.seasonNumber,
                  stillPath = episodeRealm.stillPath,
                  voteAverage = episodeRealm.voteAverage,
                  voteCount = episodeRealm.voteCount
              )
            }.toList()
        )
      }
    }
  }

  private fun getPosterUrl(serie: SerieRealm): String {
    return if (serie.backdropPath != "") {
      serie.backdropPath
    } else {
      serie.posterPath
    }
  }
}