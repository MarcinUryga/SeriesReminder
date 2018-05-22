package seriesreminder.di

import android.content.Context
import seriesreminder.network.HttpConstants
import seriesreminder.network.SerieDetailsAPI
import seriesreminder.network.SeriesOnTheAirAPI
import seriesreminder.repositories.RealmManager
import seriesreminder.sharedprefs.PreferencesManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import seriesreminder.SeriesReminderApplication
import javax.inject.Singleton

/**
 ** Created by marci on 2018-02-15.
 */

@Module
class ApplicationModule {

  @Provides
  @Singleton
  fun provideAppContext(seriesReminderApplication: SeriesReminderApplication): Context {
    return seriesReminderApplication
  }

  @Provides
  fun provideOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor { chain ->
          val request = chain.request()
          val url = request.url().newBuilder().addQueryParameter("api_key", HttpConstants.API_KEY).build()
          return@addInterceptor chain.proceed(request.newBuilder().url(url).build())
        }
        .build()
  }

  @Provides
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(HttpConstants.TMDB_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  fun provideSeriesOnTheAirAPI(retrofit: Retrofit): SeriesOnTheAirAPI {
    return retrofit.create(SeriesOnTheAirAPI::class.java)
  }

  @Provides
  fun provideSerieSeasonsAPI(retrofit: Retrofit): SerieDetailsAPI {
    return retrofit.create(SerieDetailsAPI::class.java)
  }

  @Provides
  fun provideRealmManager() = RealmManager

  @Provides
  fun providePreferencesManager(application: SeriesReminderApplication) = PreferencesManager(application)
}