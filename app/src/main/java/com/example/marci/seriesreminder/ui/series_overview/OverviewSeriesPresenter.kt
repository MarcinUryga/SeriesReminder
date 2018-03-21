package com.example.marci.seriesreminder.ui.series_overview

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
import com.example.marci.seriesreminder.ui.serie_details.GetSerieDetailsUseCase
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_overview.viewmodel.SerieViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by marci on 2018-02-15.
 */
@ScreenScope
class OverviewSeriesPresenter @Inject constructor(
    private val getSeriesOnTheAirUseCase: GetSeriesOnTheAirUseCase,
    private val subscribedSeriesStorage: SubscribedSeriesStorage,
    private val getSerieDetailsUseCase: GetSerieDetailsUseCase
) : BasePresenter<OverviewSeriesContract.View>(), OverviewSeriesContract.Presenter {

  private var currentSeriesPage = 1
  private var loading = false
  private var totalPages = 0

  override fun resume() {
    super.resume()
    currentSeriesPage = 1
    view.clearSeriesAdapter()
    downloadNewSeries()
  }

  override fun onScrolledItems(itemPosition: Int) {
    if (isScrolledAllSeries(itemPosition)) {
      downloadNewSeries(currentSeriesPage)
    }
  }

  private fun isScrolledAllSeries(itemPosition: Int): Boolean {
    return !loading &&
        view.getSeriesListSize() == (itemPosition + 1) &&
        currentSeriesPage <= totalPages
  }

  override fun downloadNewSeries(page: Int) {
    val disposable = getSeriesOnTheAirUseCase.getSeriesPage(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { doOnLoadingSeriesFromCurrentPage() }
        .doFinally { doAferLoadingCurrentPage() }
        .subscribe { seriesPage ->
          if (seriesPage.page != null) {
            totalPages = seriesPage.totalPages.let { it!! }
            view.addSeries(seriesPage.results?.map {
              SerieViewModel(
                  id = it.id!!,
                  title = it.name!!,
                  originCountry = it.originCountry,
                  voteCount = it.voteCount!!,
                  originalLanguage = it.originalLanguage!!,
                  voteAverage = it.voteAverage!!,
                  overview = it.overview!!,
                  photoUrl = it.posterPath!!,
                  isSubscribed = subscribedSeriesStorage.getSerie(it.id.toString()) ?: false
              )
            }.let { it!! })
          }
        }
    disposables?.add(disposable)
  }

  private fun doAferLoadingCurrentPage() {
    loading = false
    view.removeProgressBar()
    currentSeriesPage++
  }

  private fun doOnLoadingSeriesFromCurrentPage() {
    loading = true
    view.addProgressBar()
  }

  override fun handleChosenSerie(clickedSerie: Observable<Int>) {
    val disposable = clickedSerie.subscribe { serieId ->
      view.startSerieDetailsActivity(SerieIdParams(serieId = serieId))
    }
    disposables?.add(disposable)
  }

  override fun handleSubscriptionSerie(clickedSubscription: Observable<SerieViewModel>) {
    val disposable = clickedSubscription.subscribe { serie ->
      if (!serie.isSubscribed) {
        subscribedSeriesStorage.subscribeSerie(serie.id.toString())
        view.removeSerieFromRecyclerView(serie.id)
        updateSerieDetails(serie.id)
      } else {
        subscribedSeriesStorage.unsubscribeSerie(serie.id.toString())
      }
    }
    disposables?.add(disposable)
  }

  private fun updateSerieDetails(serieId: Int) {
    val disposable = getSerieDetailsUseCase.updateAndGet(serieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result ->
          view.showToast("Subscribed ${result.name}")
        }
    disposables?.add(disposable)
  }
}