package com.davoh.starwarsapi_mvvm.di

import android.app.Application

class MainApplication: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)
}