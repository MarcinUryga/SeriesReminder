package com.example.marci.seriesreminder.ui.serie_details.adapter

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.start_item_episode.view.*

/**
 * Created by marci on 2018-02-22.
 */
class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  @SuppressLint("SetTextI18n")
  fun bind(episode: Episode) {
    with(itemView) {
      Picasso.with(context).load(episode.stillPath).placeholder(ContextCompat.getDrawable(context, R.drawable.ic_movie_placeholder)).into(episodeImageView)
      episodeTitle.text = "${episode.episodeNumber}. ${episode.name}"
      airDate.text = episode.airDate
    }
  }

  companion object {
    fun create(parent: ViewGroup): EpisodesViewHolder {
      return EpisodesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.start_item_episode, parent, false))
    }
  }
}