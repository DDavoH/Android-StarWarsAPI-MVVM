package com.davoh.starwarsapi_mvvm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davoh.starwarsapi_mvvm.domain.Character
import com.davoh.starwarsapi_mvvm.presentation.utils.Event
import com.davoh.starwarsapi_mvvm.usecases.GetCharactersUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import com.davoh.starwarsapi_mvvm.presentation.CharactersViewModel.CharactersNavigation.*

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<CharactersNavigation>>()
    val events: LiveData<Event<CharactersNavigation>> get() = _events

    private var _page:Int = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun loadCharacters(){
        disposable.add(
            getCharactersUseCase.invoke(_page)
                .doOnSubscribe { showLoading() }
                .subscribe(
                    {list->
                        if (list.size< PAGE_SIZE) {
                            isLastPage = true
                        }
                        _events.value = Event(ShowCharactersList(list))
                        _page+=1
                        isLastPage = false
                        hideLoading()
                    },
                    {error->
                        isLastPage = true
                        hideLoading()
                        _events.value = Event(ShowCharactersError(error))
                    })
        )
    }

    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
    }

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }
        loadCharacters()
    }

    private fun showLoading() {
        isLoading = true
        _events.value = Event(ShowLoading)
    }

    private fun hideLoading() {
        isLoading = false
        _events.value = Event(HideLoading)
    }

    companion object {
        private const val PAGE_SIZE = 10
    }

    sealed class CharactersNavigation {
        data class ShowCharactersError(val error: Throwable) : CharactersNavigation()
        data class ShowCharactersList(val charactersList: List<Character>) : CharactersNavigation()
        object HideLoading : CharactersNavigation()
        object ShowLoading : CharactersNavigation()
    }

}