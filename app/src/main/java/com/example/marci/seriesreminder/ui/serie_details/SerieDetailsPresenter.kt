package com.example.marci.seriesreminder.ui.serie_details

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
import com.example.marci.seriesreminder.ui.serie_details.adapter.ClickedEpisode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by marci on 2018-02-21.
 */
@ScreenScope
class SerieDetailsPresenter @Inject constructor(
    private val subscribedSeriesStorage: SubscribedSeriesStorage,
    private val getSerieDetailsUseCase: GetSerieDetailsUseCase,
    private val serieIdParams: SerieIdParams
) : BasePresenter<SerieDetailsContract.View>(), SerieDetailsContract.Presenter {

  private lateinit var floatingActionButtonState: FloatingActionButtonState

  override fun resume() {
    super.resume()
    val disposable = getSerieDetailsUseCase.updateAndGet(serieIdParams.serieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ serieDetails ->
          view.showSerieDetails(serieDetails)
          updateActionButtonState(serieDetails.id)
        })
    disposables?.add(disposable)
  }

  private fun updateActionButtonState(id: Int) {
    floatingActionButtonState = when (subscribedSeriesStorage.getSerie(id.toString())) {
      null -> FloatingActionButtonState.SUBSCRIBE
      else -> FloatingActionButtonState.UNSUBSCRIBE
    }
    view.updateActionButton(floatingActionButtonState)
  }

  override fun onActionButtonClicked(id: Int) {
    when (floatingActionButtonState) {
      FloatingActionButtonState.SUBSCRIBE -> subscribedSeriesStorage.subscribeSerie(id.toString())
      else -> subscribedSeriesStorage.unsubscribeSerie(id.toString())
    }
    updateActionButtonState(id)
  }

  override fun handleOnBackIconClicked() {
    view.startMainActivity()
  }

  override fun handleClickedEpisode(clickedEpisode: Observable<ClickedEpisode>) {
    val disposable = clickedEpisode.subscribe { clickedEpisode ->
      view.startEpisodeDetails(clickedEpisode)
    }
    disposables?.add(disposable)
  }
}