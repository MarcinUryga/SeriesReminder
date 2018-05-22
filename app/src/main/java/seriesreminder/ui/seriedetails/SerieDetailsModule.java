package seriesreminder.ui.seriedetails;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 ** Created by marci on 2018-02-22.
 */
@Module
abstract public class SerieDetailsModule {

  @Binds
  abstract SerieDetailsContract.View bindView(SerieDetailsActivity view);

  @Binds
  abstract SerieDetailsContract.Presenter bindPresenter(SerieDetailsPresenter presenter);

  @Provides
  static SerieIdParams provideSerieId(SerieDetailsActivity view) {
    return new SerieIdParams(view.getIntent().getExtras());
  }
}
