package com.davoh.starwarsapi_mvvm.framework.requestmanager


import com.davoh.starwarsapi_mvvm.domain.*

fun CharacterResponse.toDomainCharacterList(): List<Character> = this.characters.toDomainCharacterList()

fun List<CharacterDetailsResponse>.toDomainCharacterList(): List<Character> = this.map{
    Character(it.name,it.height, it.mass, it.hairColor, it.skinColor, it.eyeColor, it.birthYear, it.gender,
    it.homeWorld, it.vehicles)
}

fun PlanetResponse.toDomainPlanetList(): List<Planet> = this.planets.toDomainPlanetList()

fun List<PlanetDetailsResponse>.toDomainPlanetList(): List<Planet> = this.map{
    Planet(it.name,it.rotationPeriod,it.orbitalPeriod,it.diameter,it.gravity,it.terrain,it.surfaceWater,
    it.population)
}

fun VehicleResponse.toDomainVehicleList():List<Vehicle> = this.vehicles.toDomainVehicleList()

fun List<VehicleDetailsResponse>.toDomainVehicleList(): List<Vehicle> = this.map{
    Vehicle(it.name,it.model,it.manufacturer,it.cost,it.maxAtmospheringSpeed,it.crew,it.passengers,it.cargoCapacity,
    it.consumables,it.vehicleClass)
}