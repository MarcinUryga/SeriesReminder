package com.example.marci.seriesreminder.ui.episode;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by marci on 2018-04-13.
 */

@Module
public abstract class EpisodeModule {

  @Binds
  abstract EpisodeContract.View bindView(EpisodeActivity view);

  @Binds
  abstract EpisodeContract.Presenter bindPresenter(EpisodePresenter presenter);

  @Provides
  public static EpisodeIdParam provideEpiosdeIdParam(EpisodeActivity view) {
    return new EpisodeIdParam(view.getIntent().getExtras());
  }
}
