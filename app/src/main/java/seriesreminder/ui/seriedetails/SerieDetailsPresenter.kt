package seriesreminder.ui.seriedetails

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import seriesreminder.di.ScreenScope
import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.mvp.BasePresenter
import seriesreminder.ui.common.SubscribedSeriesStorage
import seriesreminder.ui.seriedetails.adapter.ClickedEpisode
import javax.inject.Inject

/**
 ** Created by marci on 2018-02-21.
 */
@ScreenScope
class SerieDetailsPresenter @Inject constructor(
    private val subscribedSeriesStorage: SubscribedSeriesStorage,
    private val getSerieDetailsUseCase: GetSerieDetailsUseCase,
    private val serieIdParams: SerieIdParams
) : BasePresenter<SerieDetailsContract.View>(), SerieDetailsContract.Presenter {

  private lateinit var floatingActionButtonState: FloatingActionButtonState

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getSerieDetailsUseCase.updateAndGet(serieIdParams.serieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
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

  override fun handleEpisodesPart(episodes: List<Episode>) {
    if (episodes.isEmpty()) {
      view.hideEpisodesView()
    } else {
      view.showEpisodes()
    }
  }

  override fun handleOnBackIconClicked() {
    view.startMainActivity()
  }

  override fun handleClickedEpisode(clickedEpisode: Observable<ClickedEpisode>) {
    val disposable = clickedEpisode.subscribe { episode ->
      view.startEpisodeDetails(episode)
    }
    disposables?.add(disposable)
  }
}