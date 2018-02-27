package com.example.marci.seriesreminder.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-02-20.
 */
open class SerieRealm : RealmObject() {

  @PrimaryKey
  var id: Int = 0
  open var name: String = ""
  open var originCountry: RealmList<String>? = null
  open var voteCount: Int = 0
  open var originalLanguage: String = ""
  open var voteAverage: Double = 0.0
  open var overview: String = ""
  open var posterPath: String = ""
  open var backdropPath: String = ""
  open var numberOfSeasons: Int = 0
  open var episodes: RealmList<EpisodeRealm>? = null
}