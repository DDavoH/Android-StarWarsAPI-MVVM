package com.davoh.starwarsapi_mvvm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davoh.starwarsapi_mvvm.domain.Vehicle
import com.davoh.starwarsapi_mvvm.presentation.utils.Event
import com.davoh.starwarsapi_mvvm.usecases.GetVehiclesUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import com.davoh.starwarsapi_mvvm.presentation.VehiclesViewModel.VehiclesNavigation.*

class VehiclesViewModel(
    private val getVehiclesUseCase: GetVehiclesUseCase
): ViewModel() {

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<VehiclesNavigation>>()
    val events: LiveData<Event<VehiclesNavigation>> get() = _events

    private var _page:Int = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun loadVehicles(){
        disposable.add(
            getVehiclesUseCase
                .invoke(_page)
                .doOnSubscribe { showLoading() }
                .subscribe(
                    {list->
                        if (list.size< PAGE_SIZE) {
                            isLastPage = true
                        }
                        _events.value = Event(ShowVehiclesList(list))
                        _page+=1
                        isLastPage = false
                        hideLoading()
                    },
                    {error->
                        isLastPage = true
                        hideLoading()
                        _events.value = Event(ShowVehiclesError(error))
                    })
        )
    }

    fun onRetryGetAllVehicles() {
        _page=1
        loadVehicles()
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
        loadVehicles()
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

    sealed class VehiclesNavigation {
        data class ShowVehiclesError(val error: Throwable) : VehiclesNavigation()
        data class ShowVehiclesList(val vehicleList: List<Vehicle>) : VehiclesNavigation()
        object HideLoading : VehiclesNavigation()
        object ShowLoading : VehiclesNavigation()
    }
}