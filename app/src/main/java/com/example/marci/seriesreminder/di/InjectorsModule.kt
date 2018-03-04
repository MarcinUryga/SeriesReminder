package com.example.marci.seriesreminder.di

import com.example.marci.seriesreminder.receiver.SeriesAlarmModule
import com.example.marci.seriesreminder.receiver.SeriesAlarmReceiver
import com.example.marci.seriesreminder.ui.menu_navigation.MainNavigationActivity
import com.example.marci.seriesreminder.ui.menu_navigation.MainNavigationModule
import com.example.marci.seriesreminder.ui.serie_details.SerieDetailsActivity
import com.example.marci.seriesreminder.ui.serie_details.SerieDetailsModule
import com.example.marci.seriesreminder.ui.series_overview.OverviewSeriesFragment
import com.example.marci.seriesreminder.ui.series_overview.OverviewSeriesModule
import com.example.marci.seriesreminder.ui.series_watchlist.WatchlistSeriesFragment
import com.example.marci.seriesreminder.ui.series_watchlist.WatchlistSeriesModule
import com.example.marci.seriesreminder.ui.settings.SettingsFragment
import com.example.marci.seriesreminder.ui.settings.SettingsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by marci on 2018-02-15.
 */

@Module
abstract class InjectorsModule {

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(MainNavigationModule::class))
  abstract fun mainNavigationActivity(): MainNavigationActivity

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(OverviewSeriesModule::class))
  abstract fun overviewSeriesFragment(): OverviewSeriesFragment

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(SerieDetailsModule::class))
  abstract fun serieDetailsActivity(): SerieDetailsActivity

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(WatchlistSeriesModule::class))
  abstract fun watchlistSeriesFragment(): WatchlistSeriesFragment

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(SettingsModule::class))
  abstract fun settingsFragment(): SettingsFragment

  @ContributesAndroidInjector(modules = arrayOf(SeriesAlarmModule::class))
  abstract fun seriesAlarmReceiver(): SeriesAlarmReceiver
}