package com.example.marci.seriesreminder.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-02-27.
 */
open class SeasonRealm: RealmObject() {
  @PrimaryKey
  open var id: Int = 0
  open var episodeCount: Int = 0
  open var posterPath: String = ""
  open var seasonNumber: Int = 0
}