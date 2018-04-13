package com.example.marci.seriesreminder.ui.menu_navigation

import android.os.Bundle

/**
 * Created by marci on 2018-04-02.
 */
class MenuItemParams(bundle: Bundle? = Bundle()) {

  val data: Bundle = bundle ?: Bundle()

  var menuItem: Int
    get() = data.getInt(MENU_ITEM)
    set(value) = data.putInt(MENU_ITEM, value)

  constructor(menuItem: Int) : this() {
    this.menuItem = menuItem
  }

  companion object {
    const val MENU_ITEM: String = "menuItem"
  }
}