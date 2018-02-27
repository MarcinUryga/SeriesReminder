package com.example.marci.seriesreminder.ui.series_watchlist

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel

/**
 * Created by marci on 2018-02-16.
 */
interface WatchlistSeriesContract {

  interface View : MvpView {

    fun showSeries(series: MutableList<SubscribedSerieViewModel>)
  }

  interface Presenter : MvpPresenter
}