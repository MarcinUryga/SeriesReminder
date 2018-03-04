package com.example.marci.seriesreminder.receiver

import dagger.Binds
import dagger.Module

/**
 * Created by marci on 2018-03-02.
 */

@Module
abstract class SeriesAlarmModule {

  @Binds
  abstract fun bindReceiver(receiver: SeriesAlarmReceiver): SeriesAlarmContract.Receiver

  @Binds
  abstract fun bindPresenter(presenter: SeriesAlarmPresenter): SeriesAlarmContract.Presenter
}