package com.davoh.starwarsapi_mvvm.data.di

import com.davoh.starwarsapi_mvvm.data.*
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun characterRepositoryProvider(
        charactersDataSource: RemoteCharactersDataSource
    ) = CharacterRepository(charactersDataSource)

    @Provides
    fun planetRepositoryProvider(
        planetsDataSource: RemotePlanetsDataSource
    )= PlanetRepository(planetsDataSource)

    @Provides
    fun vehicleRepositoryProvider(
        vehicleDataSource: RemoteVehiclesDataSource
    ) = VehicleRepository(vehicleDataSource)

}