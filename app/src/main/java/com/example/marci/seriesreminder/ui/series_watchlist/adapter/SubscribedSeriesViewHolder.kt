package com.example.marci.seriesreminder.ui.series_watchlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_subscribed_serie.view.*


/**
 * Created by marci on 2018-02-23.
 */
class SubscribedSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(serie: SubscribedSerieViewModel) {
    with(itemView) {
      serieName.text = serie.title
      serieAverage.text = serie.voteAverage.toString()
      serieOverview.text = serie.overview
      Picasso.with(context).load(serie.photoUrl).into(serieImage)
      voteCountTextView.text = context.getString(R.string.vote_count, serie.voteCount)
      originCountryTextView.text = context.getString(R.string.country, serie.originCountry.toString())
      if (serie.episodes.isNotEmpty()) {
        nextEpisodeDateTextView.text = context.getString(R.string.next_episode, serie.getNextEpisodeDateString())
      }
    }
  }

  companion object {
    fun create(parent: ViewGroup): SubscribedSeriesViewHolder {
      return SubscribedSeriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subscribed_serie, parent, false))
    }
  }
}