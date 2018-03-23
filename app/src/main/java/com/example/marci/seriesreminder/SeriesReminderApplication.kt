package com.example.marci.seriesreminder

import com.example.marci.seriesreminder.di.DaggerApplicationComponent
import com.example.marci.seriesreminder.repositories.RealmManager
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * Created by marci on 2018-02-15.
 */
class SeriesReminderApplication : DaggerApplication() {

  @Inject lateinit var picasso: Picasso

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
    RealmManager.initRealm(this)
    Picasso.setSingletonInstance(picasso)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerApplicationComponent.builder().create(this)
  }
}