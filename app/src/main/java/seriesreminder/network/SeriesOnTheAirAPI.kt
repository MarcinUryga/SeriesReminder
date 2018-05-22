package seriesreminder.network


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import seriesreminder.model.pojo.series.Example

/**
 ** Created by marci on 2018-02-15.
 */
interface SeriesOnTheAirAPI {

  @GET(HttpConstants.TMDB_TV_SERIES_ON_THE_AIR)
  fun getResult(@Query("page") page: Int): Single<Example>

}