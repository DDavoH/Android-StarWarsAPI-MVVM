package com.davoh.starwarsapi_mvvm.data

class CharacterRepository(
    private val charactersDataSource: RemoteCharactersDataSource
){
    fun getCharacters(page:Int) = charactersDataSource.getCharacters(page)
}

class PlanetRepository(
    private val planetsDataSource: RemotePlanetsDataSource
){
    fun getPlanets(page:Int) = planetsDataSource.getPlanets(page)
}

class VehicleRepository(
    private val vehiclesDataSource: RemoteVehiclesDataSource
){
    fun getVehicles(page:Int) = vehiclesDataSource.getVehicles(page)
}