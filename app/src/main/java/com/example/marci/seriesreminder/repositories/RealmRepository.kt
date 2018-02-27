package com.example.marci.seriesreminder.repositories

import io.realm.RealmObject
import io.realm.RealmQuery
import timber.log.Timber
import kotlin.reflect.KClass

/**
 * Created by marci on 2018-02-20.
 */
open class RealmRepository(private val realmManager: RealmManager) {

  fun copyOrUpdate(realmObject: RealmObject?) {
    val realm = realmManager.getInstance()
    try {
      realm.beginTransaction()
      if (realmObject != null) {
        realm.copyToRealmOrUpdate(realmObject)
      }
      realm.commitTransaction()
    } catch (error: IllegalArgumentException) {
      Timber.e(error, "Unsuccessfuly data save operation")
    } finally {
      realm.close()
    }
  }

  fun copyOrUpdate(realmObjects: List<RealmObject>?) {
    val realm = realmManager.getInstance()
    try {
      realm.beginTransaction()
      realmObjects?.forEach {
        realm.copyToRealmOrUpdate(it)
      }
      realm.commitTransaction()
    } catch (error: IllegalArgumentException) {
      Timber.e(error, "Unsuccessfuly data save operation")
    } finally {
      realm.close()
    }
  }

  fun <RO : RealmObject, R, Q> copyOrUpdateWithQuery(realmObjects: List<RealmObject>?, realmClass: KClass<RO>, query: RealmQuery<RO>.() -> R, queryResult: Q) {
    val realm = realmManager.getInstance()
    try {
      realm.beginTransaction()
      realmObjects?.forEach {
        if (realm.where(realmClass.java).query() == queryResult) {
          realm.insert(it)
        }
      }
      realm.commitTransaction()
    } catch (error: IllegalArgumentException) {
      Timber.e(error, "Unsuccessfuly data save operation")
    } finally {
      realm.close()
    }
  }

  fun <RO : RealmObject, R> get(realmClass: KClass<RO>, query: RealmQuery<RO>.() -> R): R {
    val realm = realmManager.getInstance()
    try {
      return realm.where(realmClass.java).query()
    } finally {
      realm.close()
    }
  }

  fun clear() {
    val realm = realmManager.getInstance()
    try {
      realm.beginTransaction()
      realm.deleteAll()
      realm.commitTransaction()
    } finally {
      realm.close()
    }
  }
}
