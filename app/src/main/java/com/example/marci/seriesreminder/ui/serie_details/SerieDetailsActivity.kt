package com.example.marci.seriesreminder.ui.serie_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.mvp.BaseActivity
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

  lateinit var episodesAdapter: EpisodesAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_serie_details)
    episodesRecyclerView.layoutManager = LinearLayoutManager(this)
  }

  override fun showSerieDetails(serieDetails: SerieDetailsViewModel) {
    toolbar.title = serieDetails.name
    Picasso.with(this).load(serieDetails.posterPath).into(serieImage)
    originCountryTextView.text = getString(R.string.country, serieDetails.originCountry)
    seasonsTextView.text = getString(R.string.seasnons, serieDetails.seasonNumber)
    serieAverage.text = "${serieDetails.voteAverage}"
    serieOverview.text = serieDetails.overview
    episodesAdapter = EpisodesAdapter(serieDetails.episodes.let { it!! })
    episodesRecyclerView.adapter = episodesAdapter
    actionSerieButton.setOnClickListener {
      presenter.onActionButtonClicked(serieDetails.id)
    }
  }

  override fun updateActionButton(floatingActionButtonState: FloatingActionButtonState) {
    actionSerieButton.setImageDrawable(ContextCompat.getDrawable(this, floatingActionButtonState.drawableId))
  }

  companion object {
    fun newIntent(context: Context, serieIdParams: SerieIdParams): Intent {
      return Intent(context, SerieDetailsActivity::class.java).putExtras(serieIdParams.data)
    }
  }
}