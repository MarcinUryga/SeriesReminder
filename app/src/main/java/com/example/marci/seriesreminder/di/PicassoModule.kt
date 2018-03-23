package com.example.marci.seriesreminder.di

import com.example.marci.seriesreminder.SeriesReminderApplication
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

/**
 * Created by marci on 2018-03-23.
 */
@Module
class PicassoModule {

  companion object {
    private const val CACHE_SIZE = 30L * 1024 * 1024 //30MB
  }

  @Provides
  fun providePicasso(
      app: SeriesReminderApplication
  ): Picasso {
    return Picasso.Builder(app)
        .downloader(OkHttp3Downloader(app, CACHE_SIZE))
        .indicatorsEnabled(true)
        .loggingEnabled(true)
        .build()
  }
}