package com.davoh.starwarsapi_mvvm.usecases

import com.davoh.starwarsapi_mvvm.data.CharacterRepository
import com.davoh.starwarsapi_mvvm.data.PlanetRepository
import com.davoh.starwarsapi_mvvm.data.VehicleRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
){
    fun invoke(page:Int) = characterRepository.getCharacters(page)
}

class GetPlanetsUseCase(
    private val planetRepository: PlanetRepository
){
    fun invoke(page:Int) = planetRepository.getPlanets(page)
}

class GetVehiclesUseCase(
    private val vehicleRepository: VehicleRepository
){
    fun invoke(page:Int) = vehicleRepository.getVehicles(page)
}