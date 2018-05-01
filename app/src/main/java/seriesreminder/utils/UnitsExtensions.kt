package seriesreminder.utils

import android.content.Context

/**
 ** Created by marci on 2018-04-17.
 */
fun Float.convertDpToPixel(context: Context): Float {
  return this * context.resources.displayMetrics.density
}

fun Float.convertPxToDp(context: Context): Float {
  return this / context.resources.displayMetrics.density
}
