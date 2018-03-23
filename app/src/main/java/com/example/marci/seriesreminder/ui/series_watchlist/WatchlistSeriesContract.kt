package com.example.marci.seriesreminder.ui.series_watchlist

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import io.reactivex.subjects.PublishSubject

/**
 * Created by marci on 2018-02-16.
 */
interface WatchlistSeriesContract {

  interface View : MvpView {

    fun refreshSeriesView(series: MutableList<SubscribedSerieViewModel>)

    fun showNoSeriesView()

    fun getAdapterItemCount(): Int

    fun starSerieDetailsActivity(serieIdParams: SerieIdParams)

    fun removeFromRecyclerView(serieId: Int)

    fun showProgressBar()

    fun hideProgressBar()
  }

  interface Presenter : MvpPresenter {

    fun handleClickedSerieUnsubscribe(subscriptionPublishSubject: PublishSubject<Int>)

    fun handleClickedSerie(clickedSeriePublishSubject: PublishSubject<Int>)
  }
}