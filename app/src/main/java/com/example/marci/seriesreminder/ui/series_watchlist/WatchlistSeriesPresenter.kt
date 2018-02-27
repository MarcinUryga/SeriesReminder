package com.example.marci.seriesreminder.ui.series_watchlist

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
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

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getSubscribedSeriesUseCase.get(subscribedSeriesStorage.getSubscribedSeriesIds())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { series ->
          if (series.isEmpty()) {
            view.showNoSeriesView()
          }
          view.showSeries(series)
        }
    disposables?.addAll(disposable)
  }

  override fun handleClickedSerie(subscriptionPublishSubject: PublishSubject<Int>) {
    val disposable = subscriptionPublishSubject.subscribe { serieId ->
      subscribedSeriesStorage.unsubscribeSerie(serieId.toString())
      if (view.getAdapterItemCount() == 0) {
        view.showNoSeriesView()
      }
    }
    disposables?.add(disposable)
  }
}