package seriesreminder.di

import seriesreminder.ui.settings.SettingsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import seriesreminder.receiver.SeriesAlarmModule
import seriesreminder.receiver.SeriesAlarmReceiver
import seriesreminder.ui.episode.EpisodeActivity
import seriesreminder.ui.episode.EpisodeModule
import seriesreminder.ui.menunavigation.MainNavigationActivity
import seriesreminder.ui.menunavigation.MainNavigationModule
import seriesreminder.ui.seriedetails.SerieDetailsActivity
import seriesreminder.ui.seriedetails.SerieDetailsModule
import seriesreminder.ui.seriesoverview.OverviewSeriesFragment
import seriesreminder.ui.seriesoverview.OverviewSeriesModule
import seriesreminder.ui.serieswatchlist.WatchlistSeriesFragment
import seriesreminder.ui.serieswatchlist.WatchlistSeriesModule
import seriesreminder.ui.settings.SettingsFragment

/**
 ** Created by marci on 2018-02-15.
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

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(EpisodeModule::class))
  abstract fun episodeActivity(): EpisodeActivity

  @ContributesAndroidInjector(modules = arrayOf(SeriesAlarmModule::class))
  abstract fun seriesAlarmReceiver(): SeriesAlarmReceiver
}