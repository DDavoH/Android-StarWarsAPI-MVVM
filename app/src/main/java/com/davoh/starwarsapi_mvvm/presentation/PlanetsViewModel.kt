package com.davoh.starwarsapi_mvvm.presentation

import androidx.lifecycle.ViewModel
import com.davoh.starwarsapi_mvvm.usecases.GetPlanetsUseCase

class PlanetsViewModel(
    private val getPlanetsUseCase: GetPlanetsUseCase
): ViewModel() {

}