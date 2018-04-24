package seriesreminder.ui.episode

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mu.marci.series_reminder.R
import seriesreminder.utils.convertDpToPixel
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_episode_details.*
import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.mvp.BaseActivity
import seriesreminder.utils.RoundedCornersTransform

/**
 ** Created by marci on 2018-04-13.
 */
class EpisodeActivity : BaseActivity<EpisodeContract.Presenter>(), EpisodeContract.View {

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_episode_details)
  }

  @SuppressLint("SetTextI18n")
  override fun showEpisode(episode: Episode) {
    Picasso.with(this)
        .load(episode.stillPath)
        .placeholder(R.drawable.ic_movie_placeholder)
        .centerCrop().resize(
        IMAGE_WIDTH.convertDpToPixel(this).toInt(),
        IMAGE_HEIGHT.convertDpToPixel(this).toInt()
    ).networkPolicy(NetworkPolicy.OFFLINE)
        .transform(RoundedCornersTransform()).into(episodeImageView)
    episodeTitle.text = "${episode.episodeNumber}. ${episode.name}"
    airDate.text = episode.airDate
    episodeOverview.text = episode.overview
  }

  companion object {
    val IMAGE_WIDTH = 200f
    val IMAGE_HEIGHT = 200f

    fun newIntent(context: Context, episodeIdParam: EpisodeIdParam): Intent {
      return Intent(context, EpisodeActivity::class.java).putExtras(episodeIdParam.data)
    }
  }
}
