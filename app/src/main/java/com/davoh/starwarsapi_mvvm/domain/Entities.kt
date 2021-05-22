package com.davoh.starwarsapi_mvvm.domain

data class Character(
    val name:String,
    val height : String,
    val mass : String,
    val hairColor: String,
    val skinColor:String,
    val eyeColor:String,
    val birthYear:String,
    val gender:String,
    val homeWorld:String,
    val vehicles:List<String>
)

data class Planet(
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod:String,
    val diameter:String,
    val gravity:String,
    val terrain:String,
    val surfaceWater:String,
    val population:String
)

data class Vehicle(
    val name:String,
    val model:String,
    val manufacturer:String,
    val cost: String,
    val maxAtmospheringSpeed: String,
    val crew : String,
    val passengers:String,
    val cargoCapacity:String,
    val consumables:String,
    val vehicleClass:String
)