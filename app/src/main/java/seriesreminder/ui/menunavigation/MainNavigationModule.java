package seriesreminder.ui.menunavigation;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 ** Created by marci on 2018-04-02.
 */
@Module
abstract public class MainNavigationModule {

  @Binds
  abstract MainNavigationContract.View bindView(MainNavigationActivity view);

  @Binds
  abstract MainNavigationContract.Presenter bindPresenter(MainNavigationPresenter presenter);

  @Provides
  static MenuItemParams provideMenuItem(MainNavigationActivity view) {
    return new MenuItemParams(view.getIntent().getExtras());
  }
}
