package com.example.marci.seriesreminder.ui.series_overview.adapter

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.network.HttpConstants
import com.example.marci.seriesreminder.ui.common.BaseViewHolder
import com.example.marci.seriesreminder.ui.series_overview.viewmodel.SerieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_serie.view.*

/**
 * Created by marci on 2018-02-16.
 */

class SeriesViewHolder(itemView: View) : BaseViewHolder(itemView) {

  fun bind(serie: SerieViewModel) {
    with(itemView) {
      serieName.text = serie.title
      serieAverage.text = serie.voteAverage.toString()
      serieOverview.text = serie.overview
      Picasso.with(context).load("${HttpConstants.TMDP_POSTER_PATH}${serie.photoUrl}").into(serieImage)
      voteCountTextView.text = context.getString(R.string.vote_count, serie.voteCount)
      originCountryTextView.text = context.getString(R.string.country, serie.originCountry.toString())
      if (serie.isSubscribed) {
        actionSubscriptionButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_remove_circle_black))
      } else {
        actionSubscriptionButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add_circle_black))
      }
    }
  }

  companion object {
    fun create(parent: ViewGroup): SeriesViewHolder {
      return SeriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false))
    }
  }
}