package com.example.marci.seriesreminder.ui.serie_details.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import kotlinx.android.synthetic.main.item_episode.view.*

/**
 * Created by marci on 2018-02-22.
 */
class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(episode: Episode) {
    with(itemView) {
      episodeTitle.text = "${episode.episodeNumber}. ${episode.name}"
      airDate.text = episode.airDate
      episodeOverview.text = episode.overview
    }
  }

  companion object {
    fun create(parent: ViewGroup): EpisodesViewHolder {
      return EpisodesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false))
    }
  }
}