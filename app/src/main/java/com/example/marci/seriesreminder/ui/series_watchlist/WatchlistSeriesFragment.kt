package com.example.marci.seriesreminder.ui.series_watchlist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.mvp.BaseFragment
import com.example.marci.seriesreminder.ui.serie_details.SerieDetailsActivity
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_watchlist.adapter.SubscribedSeriesAdapter
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_series_list.*

/**
 * Created by marci on 2018-02-16.
 */
class WatchlistSeriesFragment : BaseFragment<WatchlistSeriesContract.Presenter>(), WatchlistSeriesContract.View {

  private val subscribedSeriesAdapter = SubscribedSeriesAdapter()

  @SuppressLint("CheckResult")
  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_series_list, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    seriesRecyclerView.layoutManager = LinearLayoutManager(context)
    seriesRecyclerView.adapter = subscribedSeriesAdapter
    presenter.handleClickedSerieUnsubscribe(subscribedSeriesAdapter.getSubscriptionPublishSubject())
    presenter.handleClickedSerie(subscribedSeriesAdapter.getClickedSeriePublishSubject())
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
    seriesRecyclerView.visibility = View.INVISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
    seriesRecyclerView.visibility = View.VISIBLE
  }

  override fun refreshSeriesView(series: MutableList<SubscribedSerieViewModel>) {
    subscribedSeriesAdapter.clearSeries()
    subscribedSeriesAdapter.createSubscribeSeriesList(series)
  }

  override fun removeFromRecyclerView(serieId: Int) {
    subscribedSeriesAdapter.removeSerie(serieId)
  }

  override fun starSerieDetailsActivity(serieIdParams: SerieIdParams) {
    startActivity(SerieDetailsActivity.newIntent(context, serieIdParams))
  }

  override fun getAdapterItemCount() = subscribedSeriesAdapter.itemCount

  override fun showNoSeriesView() {
    seriesRecyclerView.visibility = View.INVISIBLE
    noSeriesView.visibility = View.VISIBLE
    noSeriesTextView.text = getString(R.string.no_favorites_series)
  }
}