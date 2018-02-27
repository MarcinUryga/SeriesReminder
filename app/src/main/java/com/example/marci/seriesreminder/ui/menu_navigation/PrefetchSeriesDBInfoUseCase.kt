package com.example.marci.seriesreminder.ui.menu_navigation

import com.example.marci.seriesreminder.repositories.SeriesRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by marci on 2018-02-21.
 */
class PrefetchSeriesDBInfoUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) {

  fun clearDB(){
    seriesRepository.clear()
  }

  fun prefetch(): Completable {
    return seriesRepository.saveAmountOfSeriesInfo()
  }
}