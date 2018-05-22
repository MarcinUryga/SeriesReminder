package seriesreminder.ui.seriesoverview

import io.reactivex.Observable
import seriesreminder.mvp.MvpPresenter
import seriesreminder.mvp.MvpView
import seriesreminder.ui.seriedetails.SerieIdParams
import seriesreminder.ui.seriesoverview.viewmodel.SerieViewModel

/**
 ** Created by marci on 2018-02-15.
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

    fun removeSerieFromRecyclerView(serieId: Int)

    fun showCenterProgressBar()

    fun hideCenterProgressBar()

    fun getLastRecyclerViewItemFromLastState(): Int

    fun scrollRecyclerViewToItem(position: Int)

    fun cleanListFromSubscribedSeries(subscribedSeriesIds: List<Int>)
  }

  interface Presenter : MvpPresenter {

    fun downloadNewSeries(page: Int = 1)

    fun onScrolledItems(itemPosition: Int)

    fun handleChosenSerie(clickedSerie: Observable<Int>)

    fun handleSubscriptionSerie(clickedSubscription: Observable<SerieViewModel>)
  }
}