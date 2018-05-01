package seriesreminder.ui.launch;

import dagger.Binds;
import dagger.Module;

/**
 * * Created by marci on 2018-04-30.
 */
@Module
public abstract class LaunchModule {

  @Binds
  public abstract LaunchContract.View bindView(LaunchActivity view);

  @Binds
  public abstract LaunchContract.Presenter bindPresenter(LaunchPresenter presenter);
}
