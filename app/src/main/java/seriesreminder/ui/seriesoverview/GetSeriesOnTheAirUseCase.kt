package seriesreminder.ui.seriesoverview

import io.reactivex.Single
import seriesreminder.model.pojo.series.Result
import seriesreminder.model.realm.SeriesPageRealm
import seriesreminder.repositories.SeriesRepository
import seriesreminder.ui.common.SubscribedSeriesStorage
import seriesreminder.utils.NetworkConnection
import javax.inject.Inject
import seriesreminder.model.pojo.details.Example as SerieDetails
import seriesreminder.model.pojo.seasons.Example as Seasons
import seriesreminder.model.pojo.series.Example as Series

/**
 ** Created by marci on 2018-02-15.
 */
class GetSeriesOnTheAirUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val networkConnection: NetworkConnection,
    private val seriesStorage: SubscribedSeriesStorage
) {

  fun getSeriesPage(page: Int = 1): Single<seriesreminder.model.pojo.series.Example> {
    return seriesRepository.let {
      if (networkConnection.isOnline()) {
        it.saveSeriesPage(page, seriesStorage.getSubscribedSeriesIds()).andThen(getSeriesFromRealm(page))
      } else {
        getSeriesFromRealm(page, seriesStorage.getSubscribedSeriesIds())
      }
    }
  }

  private fun getSeriesFromRealm(page: Int, subscribedSeries: List<Int> = emptyList()): Single<seriesreminder.model.pojo.series.Example> {
    return Single.fromCallable {
      seriesRepository.get(SeriesPageRealm::class) {
        val seriesPage = equalTo("page", page).findFirst()
        seriesreminder.model.pojo.series.Example(
            page = seriesPage?.page,
            totalPages = seriesPage?.totalPages,
            totalResults = seriesPage?.totalResults,
            results = seriesPage?.series
                ?.filter { serie ->
                  subscribedSeries.all { it != serie.id }
                }?.map {
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
