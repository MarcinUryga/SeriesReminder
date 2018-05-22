package seriesreminder.ui.launch

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import seriesreminder.di.ScreenScope
import seriesreminder.mvp.BasePresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 ** Created by marci on 2018-04-30.
 */
@ScreenScope
class LaunchPresenter @Inject constructor(
) : BasePresenter<LaunchContract.View>(), LaunchContract.Presenter {
  override fun resume() {
    super.resume()
    val disposable = Single.timer(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { view.startMainActivity() }
        .subscribe()
    disposables?.add(disposable)
  }
}