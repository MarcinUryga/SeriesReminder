package seriesreminder.ui.seriesoverview;

import dagger.Binds;
import dagger.Module;

/**
 * * Created by marci on 2018-02-15.
 */

@Module
public abstract class OverviewSeriesModule {

  @Binds
  abstract OverviewSeriesContract.View bindView(OverviewSeriesFragment view);

  @Binds
  abstract OverviewSeriesContract.Presenter bindPresenter(OverviewSeriesPresenter presenter);
}
