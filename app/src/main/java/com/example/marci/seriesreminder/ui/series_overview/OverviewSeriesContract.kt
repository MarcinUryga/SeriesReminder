package com.example.marci.seriesreminder.ui.series_overview

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_overview.viewmodel.SerieViewModel
import io.reactivex.Observable

/**
 * Created by marci on 2018-02-15.
 */
interface OverviewSeriesContract {

  interface View : MvpView {

    fun showSeries(series: List<SerieViewModel>)

    fun addSeries(series: List<SerieViewModel>)

    fun clearSeriesAdapter()

    fun showToast(msg: String)

    fun addProgressBar()

    fun removeProgressBar()

    fun startSerieDetailsActivity(serieIdParams: SerieIdParams)

    fun getSeriesListSize(): Int
  }

  interface Presenter : MvpPresenter {

    fun downloadSeries(page: Int = 1)

    fun downloadNewSeries(page: Int = 1)

    fun onScrolledItems(itemPosition: Int)

    fun handleChosenSerie(clickedSerie: Observable<Int>)

    fun handleSubscriptionSerie(clickedSubscription: Observable<SerieViewModel>)
  }
}