package com.example.marci.seriesreminder.ui.serie_details

import android.os.Bundle

/**
 * Created by marci on 2018-02-22.
 */
class SerieIdParams(bundle: Bundle? = Bundle()) {

  val data: Bundle = bundle ?: Bundle()

  var serieId: Int
    get() = data.getInt(SERIE_ID)
    set(value) = data.putInt(SERIE_ID, value)

  constructor(serieId: Int) : this() {
    this.serieId = serieId
  }

  companion object {
    const val SERIE_ID: String = "episodeId"
  }
}