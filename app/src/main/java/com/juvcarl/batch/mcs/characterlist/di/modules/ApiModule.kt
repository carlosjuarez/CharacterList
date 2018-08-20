package com.juvcarl.batch.mcs.characterlist.di.modules

import com.juvcarl.batch.mcs.characterlist.repository.ApiCall
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule{
    @Provides
    @Singleton
    fun providesApiCall(retrofit: Retrofit): ApiCall{
        return retrofit.create<ApiCall>(ApiCall::class.java)
    }
}