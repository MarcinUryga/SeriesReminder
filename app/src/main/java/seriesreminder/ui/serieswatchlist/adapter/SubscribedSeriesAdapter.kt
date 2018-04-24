package seriesreminder.ui.serieswatchlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import seriesreminder.ui.serieswatchlist.viewmodel.SubscribedSerieViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_subscribed_serie.view.*

/**
 ** Created by marci on 2018-02-23.
 */
class SubscribedSeriesAdapter : RecyclerView.Adapter<SubscribedSeriesViewHolder>() {

  private val subscribedSeries = mutableListOf<SubscribedSerieViewModel>()
  private val subscriptionPublishSubject = PublishSubject.create<Int>()
  private val clickedSeriePublishSubject = PublishSubject.create<Int>()

  fun createSubscribeSeriesList(subscribedSeries: MutableList<SubscribedSerieViewModel>) {
    this.subscribedSeries.addAll(subscribedSeries)
    notifyDataSetChanged()
  }

  fun clearSeries() {
    subscribedSeries.clear()
  }

  fun removeSerie(id: Int) {
    val iterator = subscribedSeries.iterator()
    var index = 0
    while (iterator.hasNext()) {
      val current = iterator.next()
      if (current.id == id) {
        iterator.remove()
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, itemCount)
      }
      index++
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubscribedSeriesViewHolder.create(parent)

  override fun onBindViewHolder(holder: SubscribedSeriesViewHolder, position: Int) {
    holder.bind(subscribedSeries[position])
    holder.itemView.setOnClickListener {
      clickedSeriePublishSubject.onNext(subscribedSeries[position].id)
    }
    holder.itemView.unsubscribeButton.setOnClickListener {
      subscriptionPublishSubject.onNext(subscribedSeries[position].id)
    }
  }

  fun getClickedSeriePublishSubject(): PublishSubject<Int> = clickedSeriePublishSubject

  fun getSubscriptionPublishSubject(): PublishSubject<Int> = subscriptionPublishSubject

  override fun getItemCount() = subscribedSeries.size
}