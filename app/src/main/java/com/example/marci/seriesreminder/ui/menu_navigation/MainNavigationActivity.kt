package com.example.marci.seriesreminder.ui.menu_navigation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.broadcast.AlarmReceiver
import com.example.marci.seriesreminder.mvp.BaseActivity
import com.example.marci.seriesreminder.ui.series_overview.OverviewSeriesFragment
import com.example.marci.seriesreminder.ui.series_watchlist.WatchlistSeriesFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main_navigation.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class MainNavigationActivity : BaseActivity<MainNavigationContract.Presenter>(), MainNavigationContract.View {

  lateinit var drawerToggle: ActionBarDrawerToggle
  lateinit var headerView: View

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_navigation)
    setSupportActionBar(toolbar)
    drawerToggle = setupDrawerToggle()
    drawerLayout.addDrawerListener(drawerToggle)
    setupNavigationDrawer()
    switchFragment(OverviewSeriesFragment())
    headerView = navigationView.getHeaderView(0)
    setAlarmManager()
  }

  private fun setAlarmManager() {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    calendar.set(Calendar.MINUTE, 25)
    calendar.set(Calendar.SECOND, 0)
    val intent1 = Intent(this, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
    val am = this.getSystemService(ALARM_SERVICE) as AlarmManager
    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    drawerToggle.syncState()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    drawerToggle.onConfigurationChanged(newConfig)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return if (drawerToggle.onOptionsItemSelected(item)) {
      true
    } else super.onOptionsItemSelected(item)
  }

  private fun setupDrawerToggle(): ActionBarDrawerToggle {
    return ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
  }

  private fun setupNavigationDrawer() {
    navigationView.setNavigationItemSelectedListener { menuItem ->
      when (menuItem.itemId) {
        R.id.nav_series_overview -> switchFragment(OverviewSeriesFragment())
        R.id.nav_series_watchlist -> switchFragment(WatchlistSeriesFragment())
        else -> throw Exception("Illegal fragment")
      }
      menuItem.isChecked = true
      title = menuItem.title
      drawerLayout.closeDrawers()
      true
    }
  }

  private fun switchFragment(currentFragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.navContainer, currentFragment)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .commit()
  }

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, MainNavigationActivity::class.java)
    }
  }
}
