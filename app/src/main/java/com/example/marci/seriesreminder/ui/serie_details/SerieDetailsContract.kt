package com.example.marci.seriesreminder.ui.serie_details

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView
import com.example.marci.seriesreminder.ui.serie_details.adapter.ClickedEpisode
import com.example.marci.seriesreminder.ui.serie_details.viewmodel.SerieDetailsViewModel
import io.reactivex.Observable

/**
 * Created by marci on 2018-02-21.
 */
interface SerieDetailsContract {

  interface View : MvpView {

    fun showSerieDetails(serieDetails: SerieDetailsViewModel)

    fun updateActionButton(floatingActionButtonState: FloatingActionButtonState)

    fun startMainActivity()

    fun startEpisodeDetails(clickedEpisode: ClickedEpisode)

    fun showProgressBar()

    fun hideProgressBar()
  }

  interface Presenter : MvpPresenter {

    fun onActionButtonClicked(id: Int)

    fun handleOnBackIconClicked()

    fun handleClickedEpisode(clickedEpisode: Observable<ClickedEpisode>)
  }
}