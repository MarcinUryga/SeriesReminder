package com.example.marci.seriesreminder.ui.series_overview

import com.example.marci.seriesreminder.repositories.SeriesRepository
import com.example.marci.seriesreminder.utils.NetworkConnection
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2018-02-25.
 */
class UpdateSerieDetailsUseCase @Inject constructor(
    private val networkConnection: NetworkConnection,
    private val seriesRepository: SeriesRepository
) {
/*
  fun update(menuItem: Int): Completable {
    return Single.fromCallable {
      seriesRepository.let {
        if (networkConnection.isOnline()) {
          it.saveSerieDetails(menuItem)
        }
      }
    }.toCompletable()
  }*/
}