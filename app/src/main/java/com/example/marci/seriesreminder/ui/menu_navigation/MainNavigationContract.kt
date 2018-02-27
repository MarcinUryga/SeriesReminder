package com.example.marci.seriesreminder.ui.menu_navigation

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView

/**
 * Created by marci on 2017-11-15.
 */
interface MainNavigationContract {

  interface View : MvpView

  interface Presenter : MvpPresenter
}