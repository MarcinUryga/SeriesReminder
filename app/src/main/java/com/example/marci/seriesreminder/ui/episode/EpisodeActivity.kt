package com.example.marci.seriesreminder.ui.episode

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.mvp.BaseActivity
import com.example.marci.seriesreminder.utils.RoundedCornersTransform
import com.example.marci.seriesreminder.utils.convertDpToPixel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.end_item_episode.*


/**
 * Created by marci on 2018-04-13.
 */

class EpisodeActivity : BaseActivity<EpisodeContract.Presenter>(), EpisodeContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.end_item_episode)
  }

  @SuppressLint("SetTextI18n")
  override fun showEpisode(episode: Episode) {
    Picasso.with(this).load(episode.stillPath)
        .centerCrop().resize(
        IMAGE_WIDTH.convertDpToPixel(this).toInt(),
        IMAGE_HEIGHT.convertDpToPixel(this).toInt()
    ).transform(RoundedCornersTransform()).into(episodeImageView)
    episodeTitle.text = "${episode.episodeNumber}. ${episode.name}"
    airDate.text = episode.airDate
    episodeOverview.text = episode.overview
  }

  fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
  }

  companion object {
    val IMAGE_WIDTH = 200f
    val IMAGE_HEIGHT = 200f

    fun newIntent(context: Context, episodeIdParam: EpisodeIdParam): Intent {
      return Intent(context, EpisodeActivity::class.java).putExtras(episodeIdParam.data)
    }
  }
}
