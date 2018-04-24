package seriesreminder.ui.seriedetails

import io.reactivex.Observable
import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.mvp.MvpPresenter
import seriesreminder.mvp.MvpView
import seriesreminder.ui.seriedetails.adapter.ClickedEpisode
import seriesreminder.ui.seriedetails.viewmodel.SerieDetailsViewModel

/**
 ** Created by marci on 2018-02-21.
 */
interface SerieDetailsContract {

  interface View : MvpView {

    fun showSerieDetails(serieDetails: SerieDetailsViewModel)

    fun updateActionButton(floatingActionButtonState: FloatingActionButtonState)

    fun startMainActivity()

    fun startEpisodeDetails(episode: ClickedEpisode)

    fun showProgressBar()

    fun hideProgressBar()

    fun hideEpisodesView()

    fun showEpisodes()
  }

  interface Presenter : MvpPresenter {

    fun onActionButtonClicked(id: Int)

    fun handleOnBackIconClicked()

    fun handleClickedEpisode(clickedEpisode: Observable<ClickedEpisode>)

    fun handleEpisodesPart(episodes: List<Episode>)
  }
}