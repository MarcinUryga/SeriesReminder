package com.example.marci.seriesreminder.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-02-21.
 */
open class SeriesPage : RealmObject() {
  @PrimaryKey
  open var id = 0
  open var totalPages: Int = 0
  open var totalResults: Int = 0
  open var storedPages: Int? = 0
}