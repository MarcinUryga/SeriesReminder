package seriesreminder.ui.episode

import io.reactivex.Single
import seriesreminder.model.pojo.seasons.Episode
import seriesreminder.model.realm.EpisodeRealm
import seriesreminder.repositories.SeriesRepository
import javax.inject.Inject

/**
 ** Created by marci on 2018-04-17.
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