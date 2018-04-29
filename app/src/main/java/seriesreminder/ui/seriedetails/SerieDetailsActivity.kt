package seriesreminder.ui.seriedetails

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.mu.marci.series_reminder.R
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_serie_details.*
import kotlinx.android.synthetic.main.content_serie_details.*
import seriesreminder.mvp.BaseActivity
import seriesreminder.ui.episode.EpisodeActivity
import seriesreminder.ui.episode.EpisodeIdParam
import seriesreminder.ui.seriedetails.adapter.ClickedEpisode
import seriesreminder.ui.seriedetails.adapter.EpisodesAdapter
import seriesreminder.ui.seriedetails.viewmodel.SerieDetailsViewModel

/**
 ** Created by marci on 2018-02-21.
 */
class SerieDetailsActivity : BaseActivity<SerieDetailsContract.Presenter>(), SerieDetailsContract.View {

  private var episodesAdapter = EpisodesAdapter()

  @SuppressLint("CheckResult")
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
    Picasso.with(this).load(serieDetails.backdropPath).into(serieImage)
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener {
      presenter.handleOnBackIconClicked()
    }
    originCountryTextView.text = getString(R.string.country, serieDetails.originCountry)
    seasonsTextView.text = getString(R.string.seasons, serieDetails.seasonNumber)
    serieAverage.text = "${serieDetails.voteAverage}"
    serieOverview.text = serieDetails.overview
    presenter.handleEpisodesPart(serieDetails.episodes)
    episodesAdapter.addEpisodes(serieDetails.episodes)
    episodesRecyclerView.adapter = episodesAdapter
    actionSerieButton.setOnClickListener {
      presenter.onActionButtonClicked(serieDetails.id)
    }
  }

  override fun hideEpisodesView() {
//    noEpisodesLayout.visibility = View.VISIBLE
    episodesLayout.visibility = View.INVISIBLE
  }

  override fun showEpisodes() {
//    noEpisodesLayout.visibility = View.GONE
    episodesLayout.visibility = View.VISIBLE
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