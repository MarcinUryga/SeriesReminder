package seriesreminder.ui.menunavigation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.mu.marci.series_reminder.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main_navigation.*
import kotlinx.android.synthetic.main.toolbar.*
import seriesreminder.mvp.BaseActivity
import seriesreminder.ui.seriesoverview.OverviewSeriesFragment
import seriesreminder.ui.serieswatchlist.WatchlistSeriesFragment
import seriesreminder.ui.settings.SettingsFragment

class MainNavigationActivity : BaseActivity<MainNavigationContract.Presenter>(), MainNavigationContract.View {

  private lateinit var drawerToggle: ActionBarDrawerToggle
  private lateinit var headerView: View

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_navigation)
    setSupportActionBar(toolbar)
    drawerToggle = setupDrawerToggle()
    drawerLayout.addDrawerListener(drawerToggle)
    switchFragment(OverviewSeriesFragment())
    headerView = navigationView.getHeaderView(0)
    switchFragment(OverviewSeriesFragment())
    setUpNavigationViewListener()
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

  override fun navigateWithMenuNav(menuNavId: Int) {
    val menuItem = navigationView.menu.findItem(menuNavId)
    when (menuItem.itemId) {
      R.id.nav_series_overview -> switchFragment(OverviewSeriesFragment())
      R.id.nav_series_watchlist -> switchFragment(WatchlistSeriesFragment())
      R.id.nav_settings -> switchFragment(SettingsFragment())
      else -> throw Exception("Illegal fragment")
    }
    menuItem.isChecked = true
    title = menuItem.title
    drawerLayout.closeDrawers()
  }

  private fun setupDrawerToggle(): ActionBarDrawerToggle {
    return ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
  }

  private fun setUpNavigationViewListener() {
    navigationView.setNavigationItemSelectedListener { menuItem ->
      navigateWithMenuNav(menuItem.itemId)
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
    fun newIntent(context: Context, menuItemParams: MenuItemParams): Intent {
      return Intent(context, MainNavigationActivity::class.java).putExtras(menuItemParams.data)
    }
  }
}
