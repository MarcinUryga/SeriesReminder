package com.example.marci.seriesreminder.ui.series_overview

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.mvp.BaseFragment
import com.example.marci.seriesreminder.ui.serie_details.SerieDetailsActivity
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_overview.adapter.SeriesAdapter
import com.example.marci.seriesreminder.ui.series_overview.viewmodel.SerieViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_series_list.*

class OverviewSeriesFragment : BaseFragment<OverviewSeriesContract.Presenter>(), OverviewSeriesContract.View {

  val serieAdapter: SeriesAdapter = SeriesAdapter()
  //  lateinit var serieAdapter: SeriesAdapter
  val linearLayoutManager = LinearLayoutManager(context)

  var onScrollListener: RecyclerView.OnScrollListener? = null

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_series_list, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    seriesRecyclerView.layoutManager = linearLayoutManager
    seriesRecyclerView.adapter = serieAdapter
    presenter.handleChosenSerie(serieAdapter.getClickedSerie())
    onScrollListener = object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        presenter.onScrolledItems(linearLayoutManager.findLastVisibleItemPosition())
      }
    }
    seriesRecyclerView.addOnScrollListener(onScrollListener)
  }

  override fun addSeries(series: List<SerieViewModel>) {
    serieAdapter.addSeries(series)
    presenter.handleSubscriptionSerie(serieAdapter.getClickedSubscription())
  }

  override fun clearSeriesAdapter() {
    serieAdapter.clear()
  }

  override fun addProgressBar() {
    serieAdapter.addProgressBar()
  }

  override fun removeProgressBar() {
    serieAdapter.removeProgressBar()
  }

  override fun showToast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
  }

  override fun startSerieDetailsActivity(serieIdParams: SerieIdParams) {
    startActivity(SerieDetailsActivity.newIntent(context, serieIdParams))
  }

  override fun getSeriesListSize(): Int {
    return serieAdapter.itemCount
  }

  override fun removeSerieFromRecyclerView(serieId: Int) {
    serieAdapter.removeSerie(serieId)
  }

  override fun showSeries(series: List<SerieViewModel>) {
    /* serieAdapter = SeriesAdapter(series)
     seriesRecyclerView.adapter = serieAdapter*/
  }
}
