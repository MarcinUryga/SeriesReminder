package com.example.marci.seriesreminder

import com.example.marci.seriesreminder.di.DaggerApplicationComponent
import com.example.marci.seriesreminder.repositories.RealmManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by marci on 2018-02-15.
 */
class SeriesReminderApplication : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
    RealmManager.initRealm(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerApplicationComponent.builder().create(this)
  }
}