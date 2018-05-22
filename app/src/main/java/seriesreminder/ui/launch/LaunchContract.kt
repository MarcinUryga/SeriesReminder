package seriesreminder.ui.launch

import seriesreminder.mvp.MvpPresenter
import seriesreminder.mvp.MvpView

/**
 ** Created by marci on 2018-04-30.
 */
interface LaunchContract {

  interface View : MvpView {

    fun startMainActivity()
  }

  interface Presenter : MvpPresenter
}