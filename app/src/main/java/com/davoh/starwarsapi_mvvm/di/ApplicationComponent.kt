package com.davoh.starwarsapi_mvvm.di

import android.app.Application
import com.davoh.starwarsapi_mvvm.data.di.RepositoryModule
import com.davoh.starwarsapi_mvvm.framework.requestmanager.di.APIModule
import com.davoh.starwarsapi_mvvm.usecases.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIModule::class, RepositoryModule::class, UseCaseModule::class])
interface ApplicationComponent {

    fun inject(module: MainModule) : MainComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }

}