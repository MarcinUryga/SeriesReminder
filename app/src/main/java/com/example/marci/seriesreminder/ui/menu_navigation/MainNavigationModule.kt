package com.example.marci.seriesreminder.ui.menu_navigation

import com.example.marcin.meetfriends.ui.menu_navigation.MainNavigationPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by marci on 2017-11-15.
 */
@Module
abstract class MainNavigationModule {

  @Binds
  abstract fun bindView(view: MainNavigationActivity): MainNavigationContract.View

  @Binds
  abstract fun bindPresenter(presenter: MainNavigationPresenter): MainNavigationContract.Presenter
}