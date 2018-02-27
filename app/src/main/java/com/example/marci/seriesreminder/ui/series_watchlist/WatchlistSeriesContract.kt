package com.example.marci.seriesreminder.ui.series_watchlist

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import io.reactivex.subjects.PublishSubject

/**
 * Created by marci on 2018-02-16.
 */
interface WatchlistSeriesContract {

  interface View : MvpView {

    fun showSeries(series: MutableList<SubscribedSerieViewModel>)

    fun showNoSeriesView()

    fun getAdapterItemCount() : Int
  }

  interface Presenter : MvpPresenter {

    fun handleClickedSerie(subscriptionPublishSubject: PublishSubject<Int>)
  }
}