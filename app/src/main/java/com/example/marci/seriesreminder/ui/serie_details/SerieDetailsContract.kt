package com.example.marci.seriesreminder.ui.serie_details

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView
import com.example.marci.seriesreminder.ui.serie_details.viewmodel.SerieDetailsViewModel

/**
 * Created by marci on 2018-02-21.
 */
interface SerieDetailsContract {

  interface View : MvpView {

    fun showSerieDetails(serieDetails: SerieDetailsViewModel)

    fun updateActionButton(floatingActionButtonState: FloatingActionButtonState)
  }

  interface Presenter : MvpPresenter {

    fun onActionButtonClicked(id: Int)
  }
}