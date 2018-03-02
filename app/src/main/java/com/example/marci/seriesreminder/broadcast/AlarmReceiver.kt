package com.example.marci.seriesreminder.broadcast

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.ui.menu_navigation.MainNavigationActivity


/**
 * Created by marci on 2018-03-01.
 */
class AlarmReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    val `when` = System.currentTimeMillis()
    val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notificationIntent = Intent(context, MainNavigationActivity::class.java)
    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

    val pendingIntent = PendingIntent.getActivity(context, 0,
        notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)


    val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val mNotifyBuilder = NotificationCompat.Builder(
        context)
        .setSmallIcon(R.drawable.ic_tv_black_24dp)
        .setContentTitle("Alarm Fired")
        .setContentText("Events to be Performed").setSound(alarmSound)
        .setAutoCancel(true).setWhen(`when`)
        .setContentIntent(pendingIntent)
        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
    notificationManager.notify(100, mNotifyBuilder.build())
  }
}