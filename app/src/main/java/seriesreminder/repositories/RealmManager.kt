package seriesreminder.repositories

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 ** Created by marci on 2018-02-20.
 */
object RealmManager {

  fun initRealm(context: Context) {
    Realm.init(context)
    Realm.setDefaultConfiguration(
        RealmConfiguration
            .Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
    )
  }

  fun getInstance(): Realm = Realm.getDefaultInstance()
}