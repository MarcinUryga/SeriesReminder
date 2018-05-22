package seriesreminder.ui.seriesoverview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mu.marci.series_reminder.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_series_list.*
import seriesreminder.mvp.BaseFragment
import seriesreminder.ui.seriedetails.SerieDetailsActivity
import seriesreminder.ui.seriedetails.SerieIdParams
import seriesreminder.ui.seriesoverview.adapter.SeriesAdapter
import seriesreminder.ui.seriesoverview.viewmodel.SerieViewModel

/**
 ** Created by marci on 2018-02-15.
 */
class OverviewSeriesFragment : BaseFragment<OverviewSeriesContract.Presenter>(), OverviewSeriesContract.View {

  private val serieAdapter: SeriesAdapter = SeriesAdapter()
  private val linearLayoutManager = LinearLayoutManager(context)
  private var lastItemPosition = -1
  private var onScrollListener: RecyclerView.OnScrollListener? = null

  @SuppressLint("CheckResult")
  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_series_list, container, false)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    lastItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
    outState.putInt(LAST_ITEM_POSITION, lastItemPosition)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    if (savedInstanceState != null) {
      lastItemPosition = savedInstanceState.getInt(LAST_ITEM_POSITION)
    }
  }

  override fun getLastRecyclerViewItemFromLastState() = lastItemPosition

  override fun scrollRecyclerViewToItem(position: Int) {
    seriesRecyclerView.scrollToPosition(position)
  }

  override fun cleanListFromSubscribedSeries(subscribedSeriesIds: List<Int>) {
    serieAdapter.removeSeries(subscribedSeriesIds)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    seriesRecyclerView.layoutManager = linearLayoutManager
    seriesRecyclerView.adapter = serieAdapter
    presenter.handleSubscriptionSerie(serieAdapter.getClickedSubscription())
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
  }

  override fun clearSeriesAdapter() {
    serieAdapter.clear()
  }

  override fun showCenterProgressBar() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideCenterProgressBar() {
    progressBar.visibility = View.GONE
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

  companion object {
    val LAST_ITEM_POSITION = "lastItemPosition"
  }
}
