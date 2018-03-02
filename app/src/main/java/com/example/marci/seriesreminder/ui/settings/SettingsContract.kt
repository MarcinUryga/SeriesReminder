package com.example.marci.seriesreminder.ui.settings

import com.example.marci.seriesreminder.mvp.MvpPresenter
import com.example.marci.seriesreminder.mvp.MvpView

/**
 * Created by marci on 2018-03-02.
 */
interface SettingsContract {

  interface View : MvpView {

    fun showRemindTime(reminderTime: String)
  }

  interface Presenter : MvpPresenter {

    fun setReminderTime(selectedTime: String)
  }
}