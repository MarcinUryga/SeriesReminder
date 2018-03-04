package com.example.marci.seriesreminder.receiver

import com.example.marci.seriesreminder.ui.common.GetSubscribedSeriesUseCase
import com.example.marci.seriesreminder.ui.common.SubscribedSeriesStorage
import javax.inject.Inject

/**
 * Created by marci on 2018-03-02.
 */
class SeriesAlarmPresenter @Inject constructor(
    private val subscribedSeriesUseCase: GetSubscribedSeriesUseCase,
    private val subscribedSeriesStorage: SubscribedSeriesStorage,
    private val receiver: SeriesAlarmContract.Receiver
) : SeriesAlarmContract.Presenter {

  override fun setNotifications() {
    val disposable = subscribedSeriesUseCase.get(subscribedSeriesStorage.getSubscribedSeriesIds())
        .subscribe { series ->
          series.forEach {
            receiver.createNotification(it)
          }
        }
  }
}