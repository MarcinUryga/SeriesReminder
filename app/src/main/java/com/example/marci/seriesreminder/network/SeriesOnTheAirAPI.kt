package com.example.marci.seriesreminder.network


import com.example.marci.seriesreminder.model.pojo.series.Example
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by marci on 2018-02-15.
 */
interface SeriesOnTheAirAPI {

  @GET(HttpConstants.TMDB_TV_SERIES_ON_THE_AIR)
  fun getResult(@Query("page") page: Int): Single<Example>

}