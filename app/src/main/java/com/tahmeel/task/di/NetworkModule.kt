package com.tahmeel.task.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.tahmeel.task.network.HttpRequestInterceptor
import com.tahmeel.task.network.TahmeelClient
import com.tahmeel.task.network.TahmeelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.stagingtahmeelapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTahmeelService(retrofit: Retrofit): TahmeelService {
        return retrofit.create(TahmeelService::class.java)
    }

    @Provides
    @Singleton
    fun provideTahmeelClient(tahmeelService: TahmeelService): TahmeelClient {
        return TahmeelClient(tahmeelService)
    }
}
