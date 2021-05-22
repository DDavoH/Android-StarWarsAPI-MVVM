package com.davoh.starwarsapi_mvvm.framework.requestmanager

import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.ENDPOINT_CHARACTERS
import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.ENDPOINT_PLANETS
import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.ENDPOINT_VEHICLES
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface APIServices{
    @GET(ENDPOINT_CHARACTERS)
    @FormUrlEncoded
    fun getCharacters(
        @Field("page") page:Int = 1
    ): Single<CharacterResponse>

    @GET(ENDPOINT_PLANETS)
    @FormUrlEncoded
    fun getPlanets(
        @Field("page") page:Int =  1
    ): Single<PlanetResponse>

    @GET(ENDPOINT_VEHICLES)
    @FormUrlEncoded
    fun getVehicles(
        @Field("page") page:Int = 1
    ): Single<VehicleResponse>
}