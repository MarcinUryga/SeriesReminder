package seriesreminder.ui.episode

import seriesreminder.di.ScreenScope
import seriesreminder.mvp.BasePresenter
import javax.inject.Inject

/**
 ** Created by marci on 2018-04-13.
 */
@ScreenScope
class EpisodePresenter @Inject constructor(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val episodeIdParam: EpisodeIdParam
) : BasePresenter<EpisodeContract.View>(), EpisodeContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getEpisodeUseCase.get(episodeIdParam.episodeId)
        .subscribe({ episode ->
          view.showEpisode(episode)
        })
    disposables?.add(disposable)
  }
}
