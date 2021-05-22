package com.davoh.starwarsapi_mvvm.framework.requestmanager

import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.ENDPOINT_CHARACTERS
import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.ENDPOINT_PLANETS
import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.ENDPOINT_VEHICLES
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices{
    @GET(ENDPOINT_CHARACTERS)
    fun getCharacters(
        @Query("page") page:Int = 1
    ): Single<CharacterResponse>

    @GET(ENDPOINT_PLANETS)
    fun getPlanets(
        @Query("page") page:Int =  1
    ): Single<PlanetResponse>

    @GET(ENDPOINT_VEHICLES)
    fun getVehicles(
        @Query("page") page:Int = 1
    ): Single<VehicleResponse>
}