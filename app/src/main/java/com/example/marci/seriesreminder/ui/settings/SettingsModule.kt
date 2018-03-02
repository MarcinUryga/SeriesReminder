package com.example.marci.seriesreminder.ui.settings

import dagger.Binds
import dagger.Module

/**
 * Created by marci on 2018-03-02.
 */

@Module
abstract class SettingsModule {

  @Binds
  abstract fun bindView(view: SettingsFragment): SettingsContract.View

  @Binds
  abstract fun bindPresenter(presenter: SettingsPresenter): SettingsContract.Presenter
}
