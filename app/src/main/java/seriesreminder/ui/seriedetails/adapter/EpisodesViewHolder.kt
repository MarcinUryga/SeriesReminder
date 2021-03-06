package seriesreminder.ui.seriedetails.adapter

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mu.marci.series_reminder.R
import seriesreminder.utils.convertDpToPixel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_episode.view.*
import seriesreminder.model.pojo.seasons.Episode

/**
 ** Created by marci on 2018-02-22.
 */
class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  @SuppressLint("SetTextI18n")
  fun bind(episode: Episode) {
    with(itemView) {
      Picasso.with(context)
          .load(episode.stillPath)
          .centerCrop()
          .resize(IMAGE_WIDTH.convertDpToPixel(context).toInt(), IMAGE_HEIGHT.convertDpToPixel(context).toInt())
          .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_movie_placeholder))
          .into(episodeImageView)
      episodeTitle.text = "${episode.episodeNumber}. ${episode.name}"
      airDate.text = episode.airDate
    }
  }

  companion object {
    val IMAGE_WIDTH = 200f
    val IMAGE_HEIGHT = 200f

    fun create(parent: ViewGroup): EpisodesViewHolder {
      return EpisodesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false))
    }
  }
}