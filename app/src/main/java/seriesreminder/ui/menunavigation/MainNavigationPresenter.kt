package seriesreminder.ui.menunavigation

import seriesreminder.di.ScreenScope
import seriesreminder.mvp.BasePresenter
import javax.inject.Inject

/**
 ** Created by marci on 2017-11-15.
 */
@ScreenScope
class MainNavigationPresenter @Inject constructor(
    private val menuItemParams: MenuItemParams
) : BasePresenter<MainNavigationContract.View>(), MainNavigationContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    view.navigateWithMenuNav(menuItemParams.menuItem)
  }

}