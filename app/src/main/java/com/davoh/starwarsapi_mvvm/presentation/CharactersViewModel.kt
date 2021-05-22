package com.davoh.starwarsapi_mvvm.presentation

import androidx.lifecycle.ViewModel
import com.davoh.starwarsapi_mvvm.usecases.GetCharactersUseCase

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

}