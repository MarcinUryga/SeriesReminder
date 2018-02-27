package com.example.marcin.meetfriends.ui.menu_navigation

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.menu_navigation.MainNavigationContract
import com.example.marci.seriesreminder.ui.menu_navigation.PrefetchSeriesDBInfoUseCase
import com.example.marci.seriesreminder.utils.NetworkConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2017-11-15.
 */
@ScreenScope
class MainNavigationPresenter @Inject constructor(
    private val prefetchSeriesDBInfoUseCase: PrefetchSeriesDBInfoUseCase,
    private val networkConnection: NetworkConnection
) : BasePresenter<MainNavigationContract.View>(), MainNavigationContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    if (networkConnection.isOnline()) {
//      prefetchSeriesDBInfoUseCase.clearDB()
      val disposable = prefetchSeriesDBInfoUseCase.prefetch()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnComplete { Timber.d("Synchronized") }
          .doOnError { error -> Timber.e(error, "Failed") }
          .subscribe()
      disposables?.add(disposable)
    }
  }
}