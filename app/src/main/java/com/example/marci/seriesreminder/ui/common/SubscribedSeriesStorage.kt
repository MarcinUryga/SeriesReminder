package com.example.marci.seriesreminder.ui.common

import com.example.marci.seriesreminder.shared_prefs.PreferencesManager
import com.example.marci.seriesreminder.shared_prefs.PreferencesManager.Companion.SUBSCRIBED_SERIES
import javax.inject.Inject

/**
 * Created by marci on 2018-02-23.
 */
class SubscribedSeriesStorage @Inject constructor(
    preferencesManager: PreferencesManager
) {

  private val storage = preferencesManager.createPreferences(SUBSCRIBED_SERIES)

  fun subscribeSerie(serieId: String) = storage.put(serieId, true)

  fun unsubscribeSerie(serieId: String) = storage.put(serieId, null)

  fun getSerie(serieId: String) = storage.get<Boolean>(serieId)

  fun getSubscribedSeriesIds() = storage.getAll().map { it.key.toInt() }
}