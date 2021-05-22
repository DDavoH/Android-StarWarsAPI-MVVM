package com.davoh.starwarsapi_mvvm.usecases.di

import com.davoh.starwarsapi_mvvm.data.CharacterRepository
import com.davoh.starwarsapi_mvvm.data.PlanetRepository
import com.davoh.starwarsapi_mvvm.data.VehicleRepository
import com.davoh.starwarsapi_mvvm.usecases.GetCharactersUseCase
import com.davoh.starwarsapi_mvvm.usecases.GetPlanetsUseCase
import com.davoh.starwarsapi_mvvm.usecases.GetVehiclesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getCharactersUseCaseProvider(characterRepository: CharacterRepository) =
        GetCharactersUseCase(characterRepository)

    @Provides
    fun getPlanetsUseCaseProvider(planetRepository: PlanetRepository) =
        GetPlanetsUseCase(planetRepository)

    @Provides
    fun getVehiclesUseCaseProvider(vehicleRepository: VehicleRepository) =
        GetVehiclesUseCase(vehicleRepository)

}