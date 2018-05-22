package seriesreminder

import android.annotation.SuppressLint
import com.mu.marci.series_reminder.BuildConfig
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import seriesreminder.di.DaggerApplicationComponent
import seriesreminder.repositories.RealmManager
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 ** Created by marci on 2018-02-15.
 */
class SeriesReminderApplication : DaggerApplication() {

  @Inject lateinit var picasso: Picasso

  @SuppressLint("CheckResult")
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