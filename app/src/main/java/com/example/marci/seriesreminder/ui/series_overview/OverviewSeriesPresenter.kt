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
import timber.log.Timber
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

  private val seriesList = mutableListOf<SerieViewModel>()
  private var currentSeriesPage = 1
  private var loading = false

  override fun resume() {
    super.resume()
    view.clearSeriesAdapter()
    seriesList.clear()
    getFromRepoAsSingle()
    Timber.d(getSeriesOnTheAirUseCase.getAmountOfSriesInfo().toString())
  }

  override fun downloadNewSeries(itemPosition: Int) {
    if (isScrolledAllSeries(itemPosition)) {
      getFromRepoAsSingle(currentSeriesPage)
    }
  }

  private fun isScrolledAllSeries(itemPosition: Int): Boolean {
    return !loading &&
        view.getSeriesListSize() == (itemPosition + 1) &&
        currentSeriesPage <= getSeriesOnTheAirUseCase.getAmountOfSriesInfo().totalPages!!
  }

  override fun getFromRepoAsSingle(page: Int) {
    val disposable = getSeriesOnTheAirUseCase.getFromRepoAsSingle(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { doOnLoadingSeriesFromCurrentPage() }
        .doFinally { doAferLoadingCurrentPage() }
        .subscribe { series ->
          series.map { it.isSubscribed = subscribedSeriesStorage.getSerie(it.id.toString()) ?: false }
          seriesList.addAll(series)
          view.addSeries(series)
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

  override fun handleSubscriptionSerie(clickedSubscription: Observable<Int>) {
    val disposable = clickedSubscription.subscribe { serieId ->
      subscribedSeriesStorage.subscribeSerie(serieId.toString())
      updateSerieDetails(serieId)
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

  override fun downloadSeries(page: Int) {
    /*val disposable = getSeriesOnTheAirUseCase.getSerieWithDetails(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
          if (isStartingLoadingSeries()) {
            view.addProgressBar()
          }
        }
        .doOnComplete {
          if (currentSeriesPage++ <= 13) {
            downloadSeries(currentSeriesPage)
          }
        }
        .doOnNext { view.removeProgressBar() }
        .subscribe { serie ->
          view.addSerie(serie)
          seriesList.add(serie)
          if (isStillLoadingSeriesFromPage())
            view.addProgressBar()
        }
    disposables?.add(disposable)*/
  }

  private fun isStartingLoadingSeries() = seriesList.size == 0

  private fun isStillLoadingSeriesFromPage() = seriesList.size != MAX_AMOUNT_OF_SERIES_ON_PAGE

  companion object {
    const val MAX_AMOUNT_OF_SERIES_ON_PAGE = 20
  }
}