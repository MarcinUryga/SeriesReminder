package com.example.marci.seriesreminder.receiver

import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel

/**
 * Created by marci on 2018-03-02.
 */
interface SeriesAlarmContract {

  interface Receiver {

    fun createNotification(it: SubscribedSerieViewModel)
  }

  interface Presenter {

    fun setNotifications()
  }
}