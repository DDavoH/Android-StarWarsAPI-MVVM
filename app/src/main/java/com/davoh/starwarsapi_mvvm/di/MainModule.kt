package com.davoh.starwarsapi_mvvm.di

import com.davoh.starwarsapi_mvvm.presentation.CharactersViewModel
import com.davoh.starwarsapi_mvvm.presentation.PlanetsViewModel
import com.davoh.starwarsapi_mvvm.presentation.VehiclesViewModel
import com.davoh.starwarsapi_mvvm.usecases.GetCharactersUseCase
import com.davoh.starwarsapi_mvvm.usecases.GetPlanetsUseCase
import com.davoh.starwarsapi_mvvm.usecases.GetVehiclesUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainModule{

    @Provides
    fun charactersViewModelProvider(
        getCharactersUseCase: GetCharactersUseCase
    ) = CharactersViewModel(getCharactersUseCase)

    @Provides
    fun planetsViewModelProvider(
        getPlanetsUseCase: GetPlanetsUseCase
    ) = PlanetsViewModel(getPlanetsUseCase)

    @Provides
    fun vehiclesViewModelProvider(
        getVehiclesUseCase: GetVehiclesUseCase
    ) = VehiclesViewModel(getVehiclesUseCase)


}

@Subcomponent(modules= [(MainModule::class)])
interface MainComponent {
    val charactersViewModel : CharactersViewModel
    val planetsViewModel : PlanetsViewModel
    val vehiclesViewModel : VehiclesViewModel
}