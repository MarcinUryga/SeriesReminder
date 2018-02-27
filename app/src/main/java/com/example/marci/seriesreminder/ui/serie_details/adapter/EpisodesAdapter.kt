package com.example.marci.seriesreminder.ui.serie_details.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marci.seriesreminder.model.pojo.seasons.Episode

/**
 * Created by marci on 2018-02-22.
 */
class EpisodesAdapter(
    private val episodeList: List<Episode>
) : RecyclerView.Adapter<EpisodesViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EpisodesViewHolder.create(parent)

  override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) = holder.bind(episodeList[position])

  override fun getItemCount() = episodeList.size
}