package com.davoh.starwarsapi_mvvm.di

import dagger.Module
import dagger.Subcomponent

@Module
class MainModule{
 //viewModels here
}

@Subcomponent(modules= [(MainModule::class)])
interface MainComponent {
    //viewModels here
}