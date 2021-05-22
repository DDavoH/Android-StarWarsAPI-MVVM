package com.davoh.starwarsapi_mvvm.framework.requestmanager.di

import com.davoh.starwarsapi_mvvm.data.RemoteCharactersDataSource
import com.davoh.starwarsapi_mvvm.data.RemotePlanetsDataSource
import com.davoh.starwarsapi_mvvm.data.RemoteVehiclesDataSource
import com.davoh.starwarsapi_mvvm.framework.requestmanager.APIConstants.BASE_API_URL
import com.davoh.starwarsapi_mvvm.framework.requestmanager.CharactersRetrofitDataSource
import com.davoh.starwarsapi_mvvm.framework.requestmanager.PlanetsRetrofitDataSource
import com.davoh.starwarsapi_mvvm.framework.requestmanager.StarWarsRequests
import com.davoh.starwarsapi_mvvm.framework.requestmanager.VehiclesRetrofitDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class APIModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = BASE_API_URL

    @Provides
    fun starWarsRequestProvider(
        @Named("baseUrl") baseUrl:String
    ) = StarWarsRequests(baseUrl)

    @Provides
    fun remoteCharactersRetrofitDataSourceProvider(
        starWarsRequests: StarWarsRequests
    ): RemoteCharactersDataSource = CharactersRetrofitDataSource(starWarsRequests)

    @Provides
    fun remotePlanetsRetrofitDataSourceProvider(
        starWarsRequests: StarWarsRequests
    ): RemotePlanetsDataSource = PlanetsRetrofitDataSource(starWarsRequests)

    @Provides
    fun remoteVehiclesRetrofitDataSourceProvider(
        starWarsRequests: StarWarsRequests
    ): RemoteVehiclesDataSource = VehiclesRetrofitDataSource(starWarsRequests)

}