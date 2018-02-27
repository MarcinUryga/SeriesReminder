package com.example.marci.seriesreminder.ui.series_overview

import com.example.marci.seriesreminder.model.pojo.series.Result
import com.example.marci.seriesreminder.model.realm.SeriesPageRealm
import com.example.marci.seriesreminder.repositories.SeriesRepository
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
import com.example.marci.seriesreminder.utils.NetworkConnection
import io.reactivex.Single
import javax.inject.Inject
import com.example.marci.seriesreminder.model.pojo.details.Example as SerieDetails
import com.example.marci.seriesreminder.model.pojo.seasons.Example as Seasons
import com.example.marci.seriesreminder.model.pojo.series.Example as Series

/**
 * Created by marci on 2018-02-15.
 */
class GetSeriesOnTheAirUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val networkConnection: NetworkConnection,
    private val seriesStorage: SubscribedSeriesStorage
) {

  fun getSeriesPage(page: Int = 1): Single<com.example.marci.seriesreminder.model.pojo.series.Example> {
    return seriesRepository.let {
      if (networkConnection.isOnline()) {
        it.saveSeriesPage(page, seriesStorage.getSubscribedSeriesIds()).andThen(getSeriesFromRealm(page))
      } else {
        getSeriesFromRealm(page)
      }
    }
  }

  private fun getSeriesFromRealm(page: Int): Single<com.example.marci.seriesreminder.model.pojo.series.Example> {
    return Single.fromCallable {
      seriesRepository.get(SeriesPageRealm::class) {
        val seriesPage = equalTo("page", page).findFirst()
        com.example.marci.seriesreminder.model.pojo.series.Example(
            page = seriesPage?.page,
            totalPages = seriesPage?.totalPages,
            totalResults = seriesPage?.totalResults,
            results = seriesPage?.series?.map {
              Result(
                  id = it.id,
                  name = it.name,
                  originCountry = it.originCountry?.toList(),
                  voteCount = it.voteCount,
                  originalLanguage = it.originalLanguage,
                  voteAverage = it.voteAverage,
                  overview = it.overview,
                  posterPath = it.posterPath
              )
            }
        )
      }
    }
  }
}
