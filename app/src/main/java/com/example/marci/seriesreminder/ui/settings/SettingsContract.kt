package com.example.marci.seriesreminder.ui.settings

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView

/**
 * Created by marci on 2018-03-02.
 */
interface SettingsContract {

  interface View : MvpView {

    fun showRemindTime(reminderTime: String)

    fun setDailyRemindsSwitch(state: Boolean)

    fun setUpAlarmManager(millis: Long)

    fun stopAlarmManager()
  }

  interface Presenter : MvpPresenter {

    fun setReminderTime(selectedTime: String)

    fun handleDailyRemindsSwitch(state: Boolean)
  }
}