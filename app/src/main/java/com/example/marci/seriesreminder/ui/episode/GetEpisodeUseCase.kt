package com.example.marci.seriesreminder.ui.episode

import com.example.marci.seriesreminder.model.pojo.seasons.Episode
import com.example.marci.seriesreminder.model.realm.EpisodeRealm
import com.example.marci.seriesreminder.repositories.SeriesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2018-04-17.
 */
class GetEpisodeUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) {

  fun get(id: Int): Single<Episode> {
    return Single.fromCallable {
      seriesRepository.get(EpisodeRealm::class) {
        val episode = equalTo("id", id).findFirst()
        Episode(
            id = episode?.id.let { it!! },
            name = episode?.name,
            overview = episode?.overview,
            airDate = episode?.airDate,
            episodeNumber = episode?.episodeNumber,
            seasonNumber = episode?.seasonNumber,
            stillPath = episode?.stillPath,
            voteAverage = episode?.voteAverage,
            voteCount = episode?.voteCount
        )
      }
    }
  }
}