package seriesreminder.ui.seriesoverview.adapter

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mu.marci.series_reminder.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_serie.view.*
import seriesreminder.network.HttpConstants
import seriesreminder.ui.common.BaseViewHolder
import seriesreminder.ui.seriesoverview.viewmodel.SerieViewModel

/**
 ** Created by marci on 2018-02-16.
 */
class SeriesViewHolder(itemView: View) : BaseViewHolder(itemView) {

  fun bind(serie: SerieViewModel) {
    with(itemView) {
      serieName.text = serie.title
      serieAverage.text = serie.voteAverage.toString()
      serieOverview.text = serie.overview
      Picasso.with(context)
          .load(serie.photoUrl)
          .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_movie_placeholder))
          .into(serieImage)
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