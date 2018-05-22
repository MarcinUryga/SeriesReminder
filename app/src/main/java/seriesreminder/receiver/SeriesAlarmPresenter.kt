package seriesreminder.receiver

import seriesreminder.ui.common.GetSubscribedSeriesUseCase
import seriesreminder.ui.common.SubscribedSeriesStorage
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 ** Created by marci on 2018-03-02.
 */
class SeriesAlarmPresenter @Inject constructor(
    private val subscribedSeriesUseCase: GetSubscribedSeriesUseCase,
    private val subscribedSeriesStorage: SubscribedSeriesStorage,
    private val receiver: SeriesAlarmContract.Receiver
) : SeriesAlarmContract.Presenter {

  private var disposables: CompositeDisposable? = CompositeDisposable()

  override fun setNotifications() {
    val disposable = subscribedSeriesUseCase.get(subscribedSeriesStorage.getSubscribedSeriesIds())
        .subscribe { series ->
          series.forEach {
            receiver.createNotification(it)
          }
        }
    disposables?.add(disposable)
  }

  override fun destroy() {
    disposables?.clear()
    disposables = null
  }
}