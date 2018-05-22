package seriesreminder.ui.episode

import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.mvp.MvpPresenter
import seriesreminder.mvp.MvpView

/**
 ** Created by marci on 2018-04-13.
 */
interface EpisodeContract {

  interface View : MvpView {

    fun showEpisode(episode: Episode)
  }

  interface Presenter : MvpPresenter
}