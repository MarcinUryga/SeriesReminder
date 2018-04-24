package seriesreminder.ui.episode

import android.os.Bundle

/**
 ** Created by marci on 2018-04-17.
 */
class EpisodeIdParam(bundle: Bundle? = Bundle()) {

  val data: Bundle = bundle ?: Bundle()

  var episodeId: Int
    get() = data.getInt(EPISODE_ID)
    set(value) = data.putInt(EPISODE_ID, value)

  constructor(episodeId: Int) : this() {
    this.episodeId = episodeId
  }

  companion object {
    const val EPISODE_ID: String = "episodeId"
  }
}