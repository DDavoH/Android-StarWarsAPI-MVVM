package com.davoh.starwarsapi_mvvm.presentation

import androidx.lifecycle.ViewModel
import com.davoh.starwarsapi_mvvm.usecases.GetVehiclesUseCase

class VehiclesViewModel(
    private val getVehiclesUseCase: GetVehiclesUseCase
): ViewModel() {

}