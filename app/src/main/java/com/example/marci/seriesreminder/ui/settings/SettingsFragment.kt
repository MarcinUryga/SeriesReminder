package com.example.marci.seriesreminder.ui.settings

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.mvp.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * Created by marci on 2018-03-02.
 */
class SettingsFragment : BaseFragment<SettingsContract.Presenter>(), SettingsContract.View {

  private var selectedTime = ""

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_settings, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    remindTimeTextView.text = getString(R.string.daily_reminder_time)
    setUpTimeChooserButton()
  }

  private fun setUpTimeChooserButton() {
    timeTextView.setOnClickListener {
      TimePickerDialog(
          context,
          TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            selectedTime = "$hour:$minute"
            presenter.setReminderTime(selectedTime)
          },
          timeTextView.text.split(":").first().toInt(),
          timeTextView.text.split(":").last().toInt(),
          true
      ).show()
    }
  }

  override fun showRemindTime(reminderTime: String) {
    timeTextView.text = reminderTime
  }
}