package seriesreminder.utils

import io.realm.RealmList

/**
 ** Created by marci on 2018-02-21.
 */
fun <T : Any> Iterable<T>.toRealmList(): RealmList<T> {
  val list = RealmList<T>()
  list.addAll(this)
  return list
}