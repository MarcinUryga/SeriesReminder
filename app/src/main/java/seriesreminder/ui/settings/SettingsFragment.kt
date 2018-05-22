package seriesreminder.ui.settings

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mu.marci.series_reminder.R
import seriesreminder.mvp.BaseFragment
import seriesreminder.utils.stringFormatToTwoDigitsTime
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_settings.*
import seriesreminder.receiver.SeriesAlarmReceiver

/**
 ** Created by marci on 2018-03-02.
 */
class SettingsFragment : BaseFragment<SettingsContract.Presenter>(), SettingsContract.View {

  private var selectedTime = ""

  @SuppressLint("CheckResult")
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
    setUpDailyRemindsSwitch()
    setUpTimeChooserButton()
  }

  override fun setUpAlarmManager(millis: Long) {
    val receiver = ComponentName(context, SeriesAlarmReceiver::class.java)
    val packageManager = context.packageManager
    packageManager.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, SeriesAlarmReceiver.newIntent(context), PendingIntent.FLAG_UPDATE_CURRENT)
    (context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager)
        .setInexactRepeating(AlarmManager.RTC_WAKEUP, millis, AlarmManager.INTERVAL_DAY, pendingIntent)
  }

  override fun stopAlarmManager() {
    val receiver = ComponentName(context, SeriesAlarmReceiver::class.java)
    val packageManager = context.packageManager
    packageManager.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, SeriesAlarmReceiver.newIntent(context), PendingIntent.FLAG_UPDATE_CURRENT)
    (context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager)
        .cancel(pendingIntent)
  }

  private fun setUpDailyRemindsSwitch() {
    dailyReminderSwitchLayout.setOnClickListener {
      dailyRemindsSwitch.isChecked = !dailyRemindsSwitch.isChecked
      presenter.handleDailyRemindsSwitch(dailyRemindsSwitch.isChecked)
    }
  }

  private fun setUpTimeChooserButton() {
    dailyReminderLayout.setOnClickListener {
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

  override fun setDailyRemindsSwitch(state: Boolean) {
    dailyRemindsSwitch.isChecked = state
  }

  override fun showRemindTime(reminderTime: String) {
    timeTextView.text = reminderTime.stringFormatToTwoDigitsTime()
  }
}