package seriesreminder.receiver

import seriesreminder.ui.serieswatchlist.viewmodel.SubscribedSerieViewModel

/**
** Created by marci on 2018-03-02.
*/
interface SeriesAlarmContract {

  interface Receiver {

    fun createNotification(it: SubscribedSerieViewModel)
  }

  interface Presenter {

    fun setNotifications()

    fun destroy()
  }
}