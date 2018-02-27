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
import com.example.marci.seriesreminder.ui.series_watchlist.adapter.SubscribedSeriesAdapter
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_series_list.*

/**
 * Created by marci on 2018-02-16.
 */
class WatchlistSeriesFragment : BaseFragment<WatchlistSeriesContract.Presenter>(), WatchlistSeriesContract.View {

  private lateinit var subscribedSeriesAdapter: SubscribedSeriesAdapter

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
  }

  override fun showSeries(series: MutableList<SubscribedSerieViewModel>) {
    subscribedSeriesAdapter = SubscribedSeriesAdapter(series)
    seriesRecyclerView.adapter = subscribedSeriesAdapter
  }
}