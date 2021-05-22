package com.davoh.starwarsapi_mvvm.framework.requestmanager

import com.davoh.starwarsapi_mvvm.data.RemoteCharactersDataSource
import com.davoh.starwarsapi_mvvm.data.RemotePlanetsDataSource
import com.davoh.starwarsapi_mvvm.data.RemoteVehiclesDataSource
import com.davoh.starwarsapi_mvvm.domain.Character
import com.davoh.starwarsapi_mvvm.domain.Planet
import com.davoh.starwarsapi_mvvm.domain.Vehicle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CharactersRetrofitDataSource(
    private val starWarsRequests: StarWarsRequests
): RemoteCharactersDataSource{
    override fun getCharacters(page: Int): Single<List<Character>> {
        return starWarsRequests
            .getService<APIServices>()
            .getCharacters(page)
            .map(CharacterResponse::toDomainCharacterList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}

class PlanetsRetrofitDataSource(
    private val starWarsRequests: StarWarsRequests
): RemotePlanetsDataSource{
    override fun getPlanets(page: Int): Single<List<Planet>> {
        return starWarsRequests
            .getService<APIServices>()
            .getPlanets(page)
            .map(PlanetResponse::toDomainPlanetList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}

class VehiclesRetrofitDataSource(
    private val starWarsRequests: StarWarsRequests
): RemoteVehiclesDataSource{
    override fun getVehicles(page: Int): Single<List<Vehicle>> {
        return starWarsRequests
            .getService<APIServices>()
            .getVehicles(page)
            .map(VehicleResponse::toDomainVehicleList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}