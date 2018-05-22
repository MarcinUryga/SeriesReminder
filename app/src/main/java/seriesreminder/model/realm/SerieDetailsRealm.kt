package seriesreminder.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
** Created by marci on 2018-02-27.
*/
open class SerieDetailsRealm : RealmObject() {
  @PrimaryKey
  open var id: Int = 0
/*  open var backdropPath: String = ""
  open var homepage: String = ""
  open var inProduction: Boolean? = null
  open var languages: RealmList<String>? = null
  open var lastAirDate: String = ""*/
  open var overview: String = ""
  open var name: String = ""
/*  open var numberOfEpisodes: Int = 0
  open var numberOfSeasons: Int = 0*/
  open var seasons: RealmList<SeasonRealm>? = null
/*  open var status: String = ""
  open var type: String = ""*/
  open var episodes: RealmList<EpisodeRealm>? = null
}