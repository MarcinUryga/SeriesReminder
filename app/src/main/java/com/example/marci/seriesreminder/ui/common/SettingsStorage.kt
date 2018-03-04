package com.example.marci.seriesreminder.ui.common

import com.example.marci.seriesreminder.shared_prefs.PreferencesManager
import com.example.marci.seriesreminder.shared_prefs.PreferencesManager.Companion.SERIES_SETTINGS
import javax.inject.Inject

/**
 * Created by marci on 2018-03-02.
 */
class SettingsStorage @Inject constructor(
    preferencesManager: PreferencesManager
) {

  private val storage = preferencesManager.createPreferences(SERIES_SETTINGS)

  fun putDailyRemindsState(state: Boolean) = storage.put(DAILY_REMINDS_STATE, state)

  fun getDailyRemindsState() = storage.get<Boolean>(DAILY_REMINDS_STATE)

  fun putReminderTime(remindTime: String) = storage.put(REMIND_TIME_KEY, remindTime)

  fun getReminderTime() = storage.get<String>(REMIND_TIME_KEY)

  companion object {
    const val REMIND_TIME_KEY = "remindTimeKey"
    const val DAILY_REMINDS_STATE = "dailyRemindsState"
    const val DEFAULT_REMIND_TIME = "8:00"
  }
}