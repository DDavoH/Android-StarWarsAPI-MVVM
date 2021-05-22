package com.davoh.starwarsapi_mvvm.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    @SerializedName("results")
    val characters: List<CharacterDetailsResponse>
): Parcelable

@Parcelize
data class CharacterDetailsResponse(
    @SerializedName("name")
    val name : String,
    @SerializedName("height")
    val height : String,
    @SerializedName("mass")
    val mass : String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor:String,
    @SerializedName("eye_color")
    val eyeColor:String,
    @SerializedName("birth_year")
    val birthYear:String,
    @SerializedName("gender")
    val gender:String,
    @SerializedName("homeworld")
    val homeWorld:String,
    @SerializedName("vehicles")
    val vehicles:List<String>
): Parcelable


@Parcelize
data class PlanetResponse(
    @SerializedName("results")
    val planets : List<PlanetDetailsResponse>
): Parcelable

@Parcelize
data class PlanetDetailsResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("orbital_period")
    val orbitalPeriod:String,
    @SerializedName("diameter")
    val diameter:String,
    @SerializedName("gravity")
    val gravity:String,
    @SerializedName("terrain")
    val terrain:String,
    @SerializedName("surface_water")
    val surfaceWater:String,
    @SerializedName("population")
    val population:String
):Parcelable


@Parcelize
data class VehicleResponse(
    @SerializedName("results")
    val vehicles: List<VehicleDetailsResponse>
):Parcelable

@Parcelize
data class VehicleDetailsResponse(
    @SerializedName("name")
    val name:String,
    @SerializedName("model")
    val model:String,
    @SerializedName("manufacturer")
    val manufacturer:String,
    @SerializedName("cost_in_credits")
    val cost: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    @SerializedName("crew")
    val crew : String,
    @SerializedName("passengers")
    val passengers:String,
    @SerializedName("cargo_capacity")
    val cargoCapacity:String,
    @SerializedName("consumables")
    val consumables:String,
    @SerializedName("vehicle_class")
    val vehicleClass:String
):Parcelable