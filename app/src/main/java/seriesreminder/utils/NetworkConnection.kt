package seriesreminder.utils

import android.content.Context
import android.net.ConnectivityManager
import seriesreminder.SeriesReminderApplication
import javax.inject.Inject

/**
 ** Created by marci on 2018-02-21.
 */
class NetworkConnection @Inject constructor(
    private val applicationContext: SeriesReminderApplication
) {

  fun isOnline(): Boolean {
    val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
  }
}