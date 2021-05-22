package com.davoh.starwarsapi_mvvm.data

import com.davoh.starwarsapi_mvvm.domain.Character
import com.davoh.starwarsapi_mvvm.domain.Planet
import com.davoh.starwarsapi_mvvm.domain.Vehicle
import io.reactivex.rxjava3.core.Single

interface RemoteCharactersDataSource{
    fun getCharacters(page:Int): Single<List<Character>>
}

interface RemotePlanetsDataSource{
    fun getPlanets(page:Int): Single<List<Planet>>
}

interface RemoteVehiclesDataSource{
    fun getVehicles(page:Int): Single<List<Vehicle>>
}