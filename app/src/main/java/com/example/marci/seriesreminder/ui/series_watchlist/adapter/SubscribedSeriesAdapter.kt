package com.example.marci.seriesreminder.ui.series_watchlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel

/**
 * Created by marci on 2018-02-23.
 */
class SubscribedSeriesAdapter(
    private val subscribedSeries: MutableList<SubscribedSerieViewModel>
) : RecyclerView.Adapter<SubscribedSeriesViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubscribedSeriesViewHolder.create(parent)

  override fun onBindViewHolder(holder: SubscribedSeriesViewHolder, position: Int) {
    holder.bind(subscribedSeries[position])
  }

  override fun getItemCount() = subscribedSeries.size
}