package com.example.omidPayTechTask.di

import com.example.omidPayTechTask.BuildConfig
import com.example.omidPayTechTask.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return dispatcher
    }

    @Singleton
    @Provides
    fun provideLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(provideLogging())
            .connectTimeout(60, TimeUnit.SECONDS) // connect timeout
            .writeTimeout(60, TimeUnit.SECONDS) // write timeout
            .readTimeout(60, TimeUnit.SECONDS) // read timeout
            .retryOnConnectionFailure(false)
            .dispatcher(provideDispatcher())
            .build()

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}