package com.example.marci.seriesreminder.ui.series_watchlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_subscribed_serie.view.*

/**
 * Created by marci on 2018-02-23.
 */
class SubscribedSeriesAdapter(
    private val subscribedSeries: MutableList<SubscribedSerieViewModel>
) : RecyclerView.Adapter<SubscribedSeriesViewHolder>() {

  private val subscriptionPublishSubject = PublishSubject.create<Int>()

  private fun removeSerie(position: Int) {
    subscribedSeries.remove(subscribedSeries[position])
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubscribedSeriesViewHolder.create(parent)

  override fun onBindViewHolder(holder: SubscribedSeriesViewHolder, position: Int) {
    holder.bind(subscribedSeries[position])
    holder.itemView.unsubscribeButton.setOnClickListener {
      subscriptionPublishSubject.onNext(subscribedSeries[position].id)
      removeSerie(position)
    }
//    notifyDataSetChanged()
  }

  fun getSubscriptionPublishSubject() = subscriptionPublishSubject

  override fun getItemCount() = subscribedSeries.size
}