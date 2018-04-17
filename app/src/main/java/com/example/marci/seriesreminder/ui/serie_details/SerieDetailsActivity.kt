package com.example.marci.seriesreminder.ui.serie_details

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.mvp.BaseActivity
import com.example.marci.seriesreminder.ui.episode.EpisodeActivity
import com.example.marci.seriesreminder.ui.episode.EpisodeIdParam
import com.example.marci.seriesreminder.ui.serie_details.adapter.ClickedEpisode
import com.example.marci.seriesreminder.ui.serie_details.adapter.EpisodesAdapter
import com.example.marci.seriesreminder.ui.serie_details.viewmodel.SerieDetailsViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_serie_details.*
import kotlinx.android.synthetic.main.content_serie_details.*

/**
 * Created by marci on 2018-02-21.
 */
class SerieDetailsActivity : BaseActivity<SerieDetailsContract.Presenter>(), SerieDetailsContract.View {

  private var episodesAdapter = EpisodesAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_serie_details)
    episodesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    presenter.handleClickedEpisode(episodesAdapter.getClickedEpisode())
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
    contentSerieDetails.visibility = View.INVISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
    contentSerieDetails.visibility = View.VISIBLE
  }

  override fun showSerieDetails(serieDetails: SerieDetailsViewModel) {
    toolbar.setNavigationIcon(R.drawable.ic_back)
    toolbar.title = serieDetails.name
    if (serieDetails.posterPath != "") {
      Picasso.with(this).load(serieDetails.posterPath).into(serieImage)
    }
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener {
      presenter.handleOnBackIconClicked()
    }
    originCountryTextView.text = getString(R.string.country, serieDetails.originCountry)
    seasonsTextView.text = getString(R.string.seasnons, serieDetails.seasonNumber)
    serieAverage.text = "${serieDetails.voteAverage}"
    serieOverview.text = serieDetails.overview
    episodesAdapter.addEpisodes(serieDetails.episodes.let { it!! })
    episodesRecyclerView.adapter = episodesAdapter
    actionSerieButton.setOnClickListener {
      presenter.onActionButtonClicked(serieDetails.id)
    }
  }

  override fun updateActionButton(floatingActionButtonState: FloatingActionButtonState) {
    actionSerieButton.setImageDrawable(ContextCompat.getDrawable(this, floatingActionButtonState.drawableId))
  }

  override fun startMainActivity() {
    super.onBackPressed()
  }

  override fun startEpisodeDetails(clickedEpisode: ClickedEpisode) {
    val options = ActivityOptions.makeSceneTransitionAnimation(
        this,
        clickedEpisode.imageOption,
        clickedEpisode.titleOption,
        clickedEpisode.airDataOption
    )
    startActivity(
        EpisodeActivity.newIntent(this, EpisodeIdParam(clickedEpisode.episodeId)),
        options.toBundle()
    )
  }

  companion object {
    fun newIntent(context: Context, serieIdParams: SerieIdParams): Intent {
      return Intent(context, SerieDetailsActivity::class.java).putExtras(serieIdParams.data)
    }
  }
}