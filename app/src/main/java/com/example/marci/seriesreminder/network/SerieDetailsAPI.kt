package com.example.marci.seriesreminder.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.marci.seriesreminder.model.pojo.details.Example as DetailsExample
import com.example.marci.seriesreminder.model.pojo.seasons.Example as SeasonsExample

/**
 * Created by marci on 2018-02-15.
 */
interface SerieDetailsAPI {

  @GET(HttpConstants.TMDB_TV_SERIE_DETAILS)
  fun getDetails(@Path("tv_id") tvId: Int): Single<DetailsExample>

  @GET(HttpConstants.TMDB_TV_SERIE_SEASONS)
  fun getSeason(@Path("tv_id") tvId: Int, @Path("season_number") seasonNumber: Int): Single<SeasonsExample>
}

