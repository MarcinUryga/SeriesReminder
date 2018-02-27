package com.example.marci.seriesreminder.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-02-20.
 */
open class EpisodeRealm : RealmObject() {

  @PrimaryKey
  var id: Int = 0
  var name: String = ""
  var overview: String = ""
  var airDate: String = ""
  var episodeNumber: Int = 0
  var seasonNumber: Int = 0
  var stillPath: String = ""
  var voteAverage: Double = 0.0
  var voteCount: Int = 0
}