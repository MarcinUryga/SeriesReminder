package com.example.marci.seriesreminder.ui.episode

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView

/**
 * Created by marci on 2018-04-13.
 */
interface EpisodeContract {

  interface View : MvpView {

    fun showEpisode(episode: Episode)
  }

  interface Presenter : MvpPresenter
}