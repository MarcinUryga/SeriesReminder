package com.example.marci.seriesreminder.ui.settings

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.settings.SettingsStorage.Companion.DEFAULT_REMIND_TIME
import javax.inject.Inject

/**
 * Created by marci on 2018-03-02.
 */
@ScreenScope
class SettingsPresenter @Inject constructor(
    private val settingsStorage: SettingsStorage
) : BasePresenter<SettingsContract.View>(), SettingsContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    val reminderTime = settingsStorage.getReminderTime()
    if (reminderTime != null) {
      view.showRemindTime(reminderTime)
    } else {
      setReminderTime(DEFAULT_REMIND_TIME)
    }
  }

  override fun setReminderTime(selectedTime: String) {
    settingsStorage.setReminderTime(selectedTime)
    view.showRemindTime(selectedTime)
  }
}