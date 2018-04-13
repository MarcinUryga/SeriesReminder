package com.example.marci.seriesreminder.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.ui.menu_navigation.MainNavigationActivity
import com.example.marci.seriesreminder.ui.menu_navigation.MenuItemEnum
import com.example.marci.seriesreminder.ui.menu_navigation.MenuItemParams
import com.example.marci.seriesreminder.ui.serie_details.SerieDetailsActivity
import com.example.marci.seriesreminder.ui.serie_details.SerieIdParams
import com.example.marci.seriesreminder.ui.series_watchlist.viewmodel.SubscribedSerieViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


/**
 * Created by marci on 2018-03-01.
 */
class SeriesAlarmReceiver : BroadcastReceiver(), SeriesAlarmContract.Receiver {

  @Inject lateinit var presenter: SeriesAlarmContract.Presenter
  private lateinit var context: Context

  override fun onReceive(context: Context, intent: Intent) {
    AndroidInjection.inject(this, context)
    this.context = context
    presenter.setNotifications()
  }

  override fun createNotification(it: SubscribedSerieViewModel) {
    val backIntent = MainNavigationActivity.newIntent(context, MenuItemParams(MenuItemEnum.WATCHLIST.itemId))
    backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    val intent = SerieDetailsActivity.newIntent(context, SerieIdParams(it.id))

    val pendingIntent = PendingIntent.getActivities(context, it.id, arrayOf(backIntent, intent), PendingIntent.FLAG_ONE_SHOT)

    val mNotifyBuilder = NotificationCompat.Builder(context, "seriesReminder")
        .setSmallIcon(R.drawable.ic_tv_black_24dp)
        .setContentTitle(it.title)
        .setContentText(context.getString(R.string.next_episode, it.getNextEpisodeDateString()))
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(it.id, mNotifyBuilder.build())

    /* val notificationIntent = SerieDetailsActivity.newIntent(context, MenuItemParams(it.id))
     notificationIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
     val stackBuilder = TaskStackBuilder.create(context)
     stackBuilder.addParentStack(MainNavigationActivity::class.java)
     stackBuilder.addNextIntent(notificationIntent)
     val pendingIntent = stackBuilder.getPendingIntent(it.id, PendingIntent.FLAG_UPDATE_CURRENT)
     val mNotifyBuilder = NotificationCompat.Builder(context, "seriesReminder")
         .setSmallIcon(R.drawable.ic_tv_black_24dp)
         .setContentTitle(it.title)
         .setContentText(context.getString(R.string.next_episode, it.getNextEpisodeDateString()))
         .setContentIntent(pendingIntent)
         .setAutoCancel(true)
     val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
     notificationManager.notify(it.id, mNotifyBuilder.build())*/
  }

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, SeriesAlarmReceiver::class.java)
    }
  }
}