package com.example.marci.seriesreminder.ui.episode

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by marci on 2018-04-13.
 */
@ScreenScope
class EpisodePresenter @Inject constructor(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val episodeIdParam: EpisodeIdParam
) : BasePresenter<EpisodeContract.View>(), EpisodeContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getEpisodeUseCase.get(episodeIdParam.episodeId)
        .subscribe({episode ->
          view.showEpisode(episode)
        })
    disposables?.add(disposable)
  }
}
