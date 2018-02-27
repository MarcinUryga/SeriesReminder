package com.example.marci.seriesreminder.ui.series_overview

import com.example.marci.seriesreminder.model.pojo.series.StoredSeriesPagesInfo
import com.example.marci.seriesreminder.model.realm.SerieRealm
import com.example.marci.seriesreminder.model.realm.SeriesPage
import com.example.marci.seriesreminder.repositories.SeriesRepository
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
import com.example.marci.seriesreminder.ui.series_overview.viewmodel.SerieViewModel
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

  fun getAmountOfSriesInfo(): StoredSeriesPagesInfo {
    return seriesRepository.get(SeriesPage::class) {
      StoredSeriesPagesInfo(
          totalPages = findFirst()?.totalPages,
          totalResults = findFirst()?.totalResults,
          page = findFirst()?.storedPages
      )
    }
  }

  fun getFromRepoAsSingle(page: Int = 1): Single<List<SerieViewModel>> {
    return seriesRepository.let {
      if (networkConnection.isOnline()) {
        it.saveSeriesFromCurrentPageExceptUpdated(page, seriesStorage.getSubscribedSeriesIds()).andThen(getSeriesFromRealm(page))
      } else {
        getSeriesFromRealm(page)
      }
    }
  }

  private fun getSeriesFromRealm(page: Int): Single<List<SerieViewModel>> {
    return Single.fromCallable {
      seriesRepository.get(SerieRealm::class) {
        val series = findAll().filter { it.serieFromPage == page }
        series.map {
          SerieViewModel(
              id = it.id,
              title = it.name,
              originCountry = it.originCountry?.toList(),
              voteCount = it.voteCount,
              originalLanguage = it.originalLanguage,
              voteAverage = it.voteAverage,
              overview = it.overview,
              photoUrl = it.posterPath
          )
        }
      }
    }
  }
}


/*
  fun getSerieWithDetails(page: Int): Flowable<SerieDetailsViewModel> {
    return Flowable.zip(
        getSeries(page),
        getSeason(page),
        BiFunction { result, example ->
          SerieDetailsViewModel(
              title = result.name.let { it!! },
              average = result.voteAverage.let { it!! },
              overview = result.overview.let { it!! },
              photoUrl = result.posterPath ?: HttpConstants.MOVIE_PLACEHOLDER,
              seasonNumber = example.seasonNumber.let { it!! },
              episodes = example.episodes
          )
        }
    )
  }

  fun getSeriesOnTheAirResult(page: Int = 1): Single<Series> {
    return seriesOnTheAirAPI.getResult(page)
  }

  fun getTotalPages(): Single<Int> {
    return getSeriesOnTheAirResult()
        .flatMap { result -> Single.just(result.totalPages) }
  }

  fun getSeries(page: Int): Flowable<Result> {
    return getSeriesOnTheAirResult(page)
        .toFlowable()
        .concatMap { t: Series -> Flowable.fromIterable(t.results) }
  }

  private fun getDetails(page: Int): Flowable<SerieDetails> {
    return getSeries(page)
        .concatMap { result ->
          serieDetailsAPI.getDetails(result.id.let { it!! }).toFlowable()
        }.concatMap { details: SerieDetails ->
      Flowable.just(details)
    }
  }

  private fun getSeason(page: Int): Flowable<Seasons> {
    return getDetails(page)
        .delay(1, TimeUnit.SECONDS)
        .concatMap { result ->
          serieDetailsAPI.getSeason(result.id.let { it!! }, result.seasons?.last()?.seasonNumber.let { it!! }).toFlowable().doOnError { error -> Timber.e(error.message) }
        }.concatMap { seasson: Seasons ->
      Flowable.just(seasson)
    }
  }*/