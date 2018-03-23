package com.example.marci.seriesreminder.di

import com.example.marci.seriesreminder.SeriesReminderApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by marci on 2018-02-15.
 */

@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    InjectorsModule::class,
    PicassoModule::class
))
interface ApplicationComponent : AndroidInjector<SeriesReminderApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<SeriesReminderApplication>()
}