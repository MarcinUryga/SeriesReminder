package com.example.marci.seriesreminder.ui.series_watchlist;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2018-02-16.
 */
@Module
abstract public class WatchlistSeriesModule {

  @Binds
  abstract WatchlistSeriesContract.View bindView(WatchlistSeriesFragment view);

  @Binds
  abstract WatchlistSeriesContract.Presenter bindPresenter(WatchlistSeriesPresenter presenter);
}
