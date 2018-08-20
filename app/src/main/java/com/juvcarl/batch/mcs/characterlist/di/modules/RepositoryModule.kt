package com.juvcarl.batch.mcs.characterlist.di.modules

import com.juvcarl.batch.mcs.characterlist.repository.ApiCall
import com.juvcarl.batch.mcs.characterlist.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule{
    @Provides
    @Singleton
    fun providesRepository(api: ApiCall): Repository{
        return Repository(api)
    }
}