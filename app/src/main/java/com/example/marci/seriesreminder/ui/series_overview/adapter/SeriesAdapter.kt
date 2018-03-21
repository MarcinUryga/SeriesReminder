package com.example.marci.seriesreminder.ui.series_overview.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marci.seriesreminder.ui.common.BaseViewHolder
import com.example.marci.seriesreminder.ui.series_overview.viewmodel.SerieViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_serie.view.*

/**
 * Created by marci on 2018-02-16.
 */
class SeriesAdapter : RecyclerView.Adapter<BaseViewHolder>() {
  private val series = mutableListOf<SerieViewModel?>()
  private val publishSubject = PublishSubject.create<Int>()
  private val subscriptionPublishSubject = PublishSubject.create<SerieViewModel>()

  fun addSeries(series: List<SerieViewModel>) {
    this.series.addAll(series)
    this.series.distinct()
    notifyDataSetChanged()
  }

  fun addProgressBar() {
    series.add(null)
    notifyItemInserted(series.lastIndex)
  }

  fun removeProgressBar() {
    val iterator = series.iterator()
    var index = 0
    while (iterator.hasNext()) {
      val current = iterator.next()
      if (current == null) {
        iterator.remove()
        notifyItemRemoved(index)
      }
      index++
    }
  }

  fun clear() {
    series.clear()
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    return when (viewType) {
      VIEW_SERIE -> SeriesViewHolder.create(parent)
      VIEW_PROGRESSBAR -> ProgressBarViewHolder.create(parent)
      else -> BaseViewHolder.empty(parent)
    }
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    when (holder.itemViewType) {
      VIEW_SERIE -> {
        (holder as? SeriesViewHolder)?.bind(series[position] as SerieViewModel)
        holder.itemView.setOnClickListener {
          publishSubject.onNext(series[position]?.id.let { it!! })
        }
        holder.itemView.actionSubscriptionButton.setOnClickListener {
          subscriptionPublishSubject.onNext(series[position].let { it!! })
        }
      }
    }
  }

  fun removeSerie(serieId: Int) {
    val iterator = series.iterator()
    var index = 0
    while (iterator.hasNext()) {
      val current = iterator.next()
      if (current?.id == serieId) {
        iterator.remove()
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, itemCount)
      }
      index++
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (series[position] == null) VIEW_PROGRESSBAR else VIEW_SERIE
  }

  override fun getItemCount() = series.size

  fun getClickedSerie(): Observable<Int> = publishSubject

  fun getClickedSubscription(): Observable<SerieViewModel> = subscriptionPublishSubject

  companion object {
    private val VIEW_SERIE = 0
    private val VIEW_PROGRESSBAR = 1
  }
}
