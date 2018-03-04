package com.example.marci.seriesreminder.ui.settings

import com.example.marci.seriesreminder.di.ScreenScope
import com.example.marci.seriesreminder.mvp.BasePresenter
import com.example.marci.seriesreminder.ui.common.SettingsStorage
import org.joda.time.DateTime
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
    setUpDefaultRemindState()
    setUpDefaultRemindTime()
    view.setDailyRemindsSwitch(settingsStorage.getDailyRemindsState().let { it!! })
    view.showRemindTime(settingsStorage.getReminderTime().let { it!! })
  }

  override fun handleDailyRemindsSwitch(state: Boolean) {
    settingsStorage.putDailyRemindsState(state)
    view.stopAlarmManager()
    if (settingsStorage.getDailyRemindsState() == true) {
      setReminderTime(settingsStorage.getReminderTime().let { it!! })
    }
    view.setDailyRemindsSwitch(state)
  }

  override fun setReminderTime(selectedTime: String) {
    settingsStorage.putReminderTime(selectedTime)
    view.showRemindTime(selectedTime)
    setUpSeriesNotificationTime()
  }

  private fun setUpSeriesNotificationTime() {
    if (settingsStorage.getDailyRemindsState() == true) {
      val today = DateTime.now()
      val clockTime = settingsStorage.getReminderTime()?.split(":")?.map { it.toInt() }
      val reminderDateTime = DateTime(today.year, today.monthOfYear, today.dayOfMonth, clockTime?.first()!!, clockTime.last())
      view.setUpAlarmManager(reminderDateTime.millis)
    }
  }

  private fun setUpDefaultRemindTime() {
    if (settingsStorage.getReminderTime() == null) {
      settingsStorage.putReminderTime(SettingsStorage.DEFAULT_REMIND_TIME)
    }
  }

  private fun setUpDefaultRemindState() {
    if (settingsStorage.getDailyRemindsState() == null) {
      settingsStorage.putDailyRemindsState(true)
    }
  }
}