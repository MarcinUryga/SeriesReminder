package seriesreminder.ui.serieswatchlist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mu.marci.series_reminder.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_series_list.*
import seriesreminder.mvp.BaseFragment
import seriesreminder.ui.seriedetails.SerieDetailsActivity
import seriesreminder.ui.seriedetails.SerieIdParams
import seriesreminder.ui.serieswatchlist.adapter.SubscribedSeriesAdapter
import seriesreminder.ui.serieswatchlist.viewmodel.SubscribedSerieViewModel

/**
 ** Created by marci on 2018-02-16.
 */
class WatchlistSeriesFragment : BaseFragment<WatchlistSeriesContract.Presenter>(), WatchlistSeriesContract.View {

  private val subscribedSeriesAdapter = SubscribedSeriesAdapter()

  @SuppressLint("CheckResult")
  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_series_list, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    seriesRecyclerView.layoutManager = LinearLayoutManager(context)
    seriesRecyclerView.adapter = subscribedSeriesAdapter
    presenter.handleClickedSerieUnsubscribe(subscribedSeriesAdapter.getSubscriptionPublishSubject())
    presenter.handleClickedSerie(subscribedSeriesAdapter.getClickedSeriePublishSubject())
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
    seriesRecyclerView.visibility = View.INVISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
    seriesRecyclerView.visibility = View.VISIBLE
  }

  override fun refreshSeriesView(series: MutableList<SubscribedSerieViewModel>) {
    subscribedSeriesAdapter.clearSeries()
    subscribedSeriesAdapter.createSubscribeSeriesList(series)
  }

  override fun removeFromRecyclerView(serieId: Int) {
    subscribedSeriesAdapter.removeSerie(serieId)
  }

  override fun starSerieDetailsActivity(serieIdParams: SerieIdParams) {
    startActivity(SerieDetailsActivity.newIntent(context, serieIdParams))
  }

  override fun getAdapterItemCount() = subscribedSeriesAdapter.itemCount

  override fun showNoSeriesView() {
    subscribedSeriesAdapter.clearSeries()
    seriesRecyclerView.visibility = View.INVISIBLE
    noSeriesView.visibility = View.VISIBLE
    noSeriesTextView.text = getString(R.string.no_favorites_series)
  }
}