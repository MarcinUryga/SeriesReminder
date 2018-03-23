package com.example.marci.seriesreminder.ui.series_watchlist

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.common.GetSubscribedSeriesUseCase
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by marci on 2018-02-16.
 */
@ScreenScope
class WatchlistSeriesPresenter @Inject constructor(
    private val getSubscribedSeriesUseCase: GetSubscribedSeriesUseCase,
    private val subscribedSeriesStorage: SubscribedSeriesStorage
) : BasePresenter<WatchlistSeriesContract.View>(), WatchlistSeriesContract.Presenter {

  override fun resume() {
    super.resume()
    val disposable = getSubscribedSeriesUseCase.get(subscribedSeriesStorage.getSubscribedSeriesIds())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe { series ->
          if (series.isEmpty()) {
            view.showNoSeriesView()
          } else {
            view.refreshSeriesView(series as MutableList<SubscribedSerieViewModel>)
          }
        }
    disposables?.addAll(disposable)
  }

  override fun handleClickedSerie(clickedSeriePublishSubject: PublishSubject<Int>) {
    val disposable = clickedSeriePublishSubject.subscribe { serieId ->
      view.starSerieDetailsActivity(SerieIdParams(serieId))
    }
    disposables?.add(disposable)
  }

  override fun handleClickedSerieUnsubscribe(subscriptionPublishSubject: PublishSubject<Int>) {
    val disposable = subscriptionPublishSubject.subscribe { serieId ->
      subscribedSeriesStorage.unsubscribeSerie(serieId.toString())
      view.removeFromRecyclerView(serieId)
      if (subscribedSeriesStorage.getSubscribedSeriesIds().isEmpty()) {
        view.showNoSeriesView()
      }
    }
    disposables?.add(disposable)
  }
}