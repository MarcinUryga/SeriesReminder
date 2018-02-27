package com.example.marci.seriesreminder.ui.serie_details

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.model.realm.SerieRealm
import com.example.marci.seriesreminder.repositories.SeriesRepository
import com.example.marci.seriesreminder.ui.serie_details.viewmodel.SerieDetailsViewModel
import com.example.marci.seriesreminder.utils.NetworkConnection
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2018-02-22.
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
            posterPath = serie.backdropPath,
            seasonNumber = serie.numberOfSeasons,
            episodes = serie.episodes?.map { episodeRealm ->
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
            }?.toList()
        )
      }
    }
  }
}