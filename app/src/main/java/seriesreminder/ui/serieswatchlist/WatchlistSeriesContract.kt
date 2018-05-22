package seriesreminder.ui.serieswatchlist

import io.reactivex.subjects.PublishSubject
import seriesreminder.mvp.MvpPresenter
import seriesreminder.mvp.MvpView
import seriesreminder.ui.seriedetails.SerieIdParams
import seriesreminder.ui.serieswatchlist.viewmodel.SubscribedSerieViewModel

/**
 ** Created by marci on 2018-02-16.
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