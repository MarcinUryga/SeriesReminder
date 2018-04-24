package seriesreminder.ui.menunavigation

import seriesreminder.mvp.MvpPresenter
import seriesreminder.mvp.MvpView

/**
 ** Created by marci on 2017-11-15.
 */
interface MainNavigationContract {

  interface View : MvpView {

    fun getCurrentFragment(menuNavId: Int)
  }

  interface Presenter : MvpPresenter
}