package com.example.marci.seriesreminder.shared_prefs

import android.content.Context
import com.example.marci.seriesreminder.SeriesReminderApplication

/**
 * Created by marci on 2018-02-23.
 */
class PreferencesManager(
    private val application: SeriesReminderApplication
) {

  fun createPreferences(preferenceFile: String = DEFAULT_PREFS_FILE) =
      PreferencesStorage(application.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE))

  companion object {
    const val DEFAULT_PREFS_FILE: String = "default_prefs"
    const val SUBSCRIBED_SERIES = "subscribed_series_prefs"
  }
}