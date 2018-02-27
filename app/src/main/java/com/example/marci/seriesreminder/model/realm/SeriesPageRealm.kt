package com.example.marci.seriesreminder.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-02-21.
 */
open class SeriesPageRealm : RealmObject() {
  @PrimaryKey
  open var page: Int = 0
  open var totalPages: Int = 0
  open var totalResults: Int = 0
  open var series: RealmList<SerieRealm>? = null
}