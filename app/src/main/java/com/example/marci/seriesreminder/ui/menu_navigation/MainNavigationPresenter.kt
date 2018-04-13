package com.example.marcin.meetfriends.ui.menu_navigation

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.menu_navigation.MainNavigationContract
import com.example.marci.seriesreminder.ui.menu_navigation.MenuItemParams
import javax.inject.Inject

/**
 * Created by marci on 2017-11-15.
 */
@ScreenScope
class MainNavigationPresenter @Inject constructor(
    private val menuItemParams: MenuItemParams
) : BasePresenter<MainNavigationContract.View>(), MainNavigationContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    view.getCurrentFragment(menuItemParams.menuItem)
  }

}