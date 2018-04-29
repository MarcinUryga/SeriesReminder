package seriesreminder.ui.serieswatchlist.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mu.marci.series_reminder.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_serie.view.*
import seriesreminder.ui.serieswatchlist.viewmodel.SubscribedSerieViewModel

/**
 ** Created by marci on 2018-02-23.
 */
class SubscribedSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(serie: SubscribedSerieViewModel) {
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
      actionSubscriptionButton.isSelected = serie.isSubscribed
      if (serie.episodes.isNotEmpty()) {
        nextEpisodeInfoLayout.visibility = View.VISIBLE
        nextEpisodeDateTextView.text = context.getString(R.string.next_episode, serie.getNextEpisodeDateString())
      }
    }
  }

  companion object {
    fun create(parent: ViewGroup): SubscribedSeriesViewHolder {
      return SubscribedSeriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false))
    }
  }
}