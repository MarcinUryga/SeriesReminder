package seriesreminder.sharedprefs

import android.content.SharedPreferences

/**
 ** Created by marci on 2018-02-23.
 */
class PreferencesStorage(
    private val prefs: SharedPreferences
) {

  fun put(key: String, value: Any?) {
    val editor = prefs.edit()
    if (value == null) {
      editor.remove(key)
    } else {
      when (value) {
        is Int -> editor.putInt(key, value)
        is Long -> editor.putLong(key, value)
        is Float -> editor.putFloat(key, value)
        is String -> editor.putString(key, value)
        is Boolean -> editor.putBoolean(key, value)
      }
    }
    editor.apply()
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> get(key: String): T? = prefs.all[key] as? T

  fun clear() {
    prefs.edit().clear().apply()
  }

  fun getAll(): Map<String, Any?> = prefs.all
}
