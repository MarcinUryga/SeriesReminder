package com.example.marci.seriesreminder.ui.series_overview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marci.seriesreminder.R
import com.example.marci.seriesreminder.ui.common.BaseViewHolder

/**
 * Created by marci on 2018-02-20.
 */
class ProgressBarViewHolder(itemView: View) : BaseViewHolder(itemView) {

  companion object {
    fun create(parent: ViewGroup): ProgressBarViewHolder {
      return ProgressBarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_progressbar, parent, false))
    }
  }
}