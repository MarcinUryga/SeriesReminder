package seriesreminder.ui.launch

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import com.mu.marci.series_reminder.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.launch_activity.*
import seriesreminder.mvp.BaseActivity
import seriesreminder.ui.menunavigation.MainNavigationActivity
import seriesreminder.ui.menunavigation.MenuItemEnum
import seriesreminder.ui.menunavigation.MenuItemParams
import seriesreminder.utils.convertPxToDp

/**
 ** Created by marci on 2018-04-30.
 */
class LaunchActivity : BaseActivity<LaunchContract.Presenter>(), LaunchContract.View {

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    setContentView(R.layout.launch_activity)
    val translationY = resources.displayMetrics.heightPixels / 2f + 300f.convertPxToDp(baseContext)
    appIcon.animate()
        .translationY(translationY)
        .setInterpolator(AccelerateInterpolator())
        .setInterpolator(BounceInterpolator()).duration = 800
    seriesTextView.animation = AnimationUtils.loadAnimation(this, R.anim.down_to_up)
  }

  override fun startMainActivity() {
    startActivity(MainNavigationActivity.newIntent(baseContext, MenuItemParams(MenuItemEnum.OVERVIEW.itemId)))
    finish()
  }
}