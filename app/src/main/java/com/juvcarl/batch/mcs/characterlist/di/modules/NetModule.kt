package com.juvcarl.batch.mcs.characterlist.di.modules

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(val baseUrl: String){

    @Provides
    @Singleton
    fun providesHttpCache(application: Application): Cache{
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(application.cacheDir,cacheSize)
        return cache
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient{
        return OkHttpClient.Builder().cache(cache).build()
    }

    @Provides
    @Singleton
    fun provideGSON(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }




}