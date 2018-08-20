package com.juvcarl.batch.mcs.characterlist.di

import android.app.Application
import android.content.Context
import com.juvcarl.batch.mcs.characterlist.di.modules.ApiModule
import com.juvcarl.batch.mcs.characterlist.di.modules.AppModule
import com.juvcarl.batch.mcs.characterlist.di.modules.NetModule
import com.juvcarl.batch.mcs.characterlist.di.modules.RepositoryModule
import com.juvcarl.batch.mcs.characterlist.repository.Repository
import com.juvcarl.batch.mcs.characterlist.viewmodel.CharacterViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AppModule::class, NetModule::class, RepositoryModule::class, ApiModule::class)
)
interface AppComponent{
    fun inject(viewModelModule: CharacterViewModel)
    fun inject(activity: Context)

    fun provideRepository(): Repository

    companion object Factory{
        fun create(app: Application, baseUrl: String): AppComponent? {
            val appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .apiModule(ApiModule())
                    .netModule(NetModule(baseUrl))
                    .repositoryModule(RepositoryModule())
                    .build()
            return appComponent
        }
    }
}