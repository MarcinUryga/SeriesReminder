package com.example.marci.seriesreminder.ui.serie_details.adapter

import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.end_item_episode.view.*

/**
 * Created by marci on 2018-02-22.
 */
class EpisodesAdapter : RecyclerView.Adapter<EpisodesViewHolder>() {
  private val publishSubject = PublishSubject.create<ClickedEpisode>()

  private lateinit var episodeList: List<Episode>

  fun addEpisodes(episodes: List<Episode>) {
    episodeList = episodes
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EpisodesViewHolder.create(parent)

  override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
    holder.bind(episodeList[position])
    holder.itemView.setOnClickListener {
      publishSubject.onNext(ClickedEpisode(
          imageOption = Pair(it.episodeImageView, it.resources.getString(R.string.imageTransition)),
          titleOption = Pair(it.episodeTitle, it.resources.getString(R.string.titleTransition)),
          airDataOption = Pair(it.airDate, it.resources.getString(R.string.airDateTransition)),
          episodeId = episodeList[position].id
      ))
    }
  }

  fun getClickedEpisode(): Observable<ClickedEpisode> = publishSubject

  override fun getItemCount() = episodeList.size
}