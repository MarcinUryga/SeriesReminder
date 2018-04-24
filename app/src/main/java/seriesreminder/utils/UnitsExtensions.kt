package seriesreminder.utils

import android.content.Context
import android.util.DisplayMetrics

/**
 ** Created by marci on 2018-04-17.
 */
fun Float.convertDpToPixel(context: Context): Float {
  val resources = context.resources
  val metrics = resources.displayMetrics
  return this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
