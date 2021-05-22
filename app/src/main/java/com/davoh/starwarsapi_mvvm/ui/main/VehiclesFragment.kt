package com.davoh.starwarsapi_mvvm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.adapters.VehicleListAdapter
import com.davoh.starwarsapi_mvvm.databinding.FragmentVehiclesBinding
import com.davoh.starwarsapi_mvvm.di.MainApplication
import com.davoh.starwarsapi_mvvm.di.MainComponent
import com.davoh.starwarsapi_mvvm.di.MainModule
import com.davoh.starwarsapi_mvvm.domain.Vehicle
import com.davoh.starwarsapi_mvvm.presentation.VehiclesViewModel
import com.davoh.starwarsapi_mvvm.presentation.VehiclesViewModel.VehiclesNavigation
import com.davoh.starwarsapi_mvvm.presentation.VehiclesViewModel.VehiclesNavigation.*
import com.davoh.starwarsapi_mvvm.presentation.utils.Event
import com.davoh.starwarsapi_mvvm.utils.getViewModel


class VehiclesFragment : Fragment() {

    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainComponent: MainComponent
    private val vehiclesViewModel: VehiclesViewModel by lazy {
        getViewModel { mainComponent.vehiclesViewModel }
    }
    private val adapter = VehicleListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vehicles, container, false)
        mainComponent = (requireActivity().applicationContext as MainApplication).appComponent.inject(MainModule())

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        adapter.setOnItemClickListener(object : VehicleListAdapter.OnItemClickListener {
            override fun onFavoriteBtnClick(vehicle: Vehicle) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVehicleDetailsFragment(vehicle))
            }
        })
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                vehiclesViewModel.onLoadMoreItems(
                    visibleItemCount,
                    firstVisibleItemPosition,
                    totalItemCount,
                )
            }
        })

        binding.swipeRefreshRv.setOnRefreshListener {
            vehiclesViewModel.onRetryGetAllVehicles()
        }

        vehiclesViewModel.loadVehicles()
        vehiclesViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        return binding.root
    }

    private fun validateEvents(event: Event<VehiclesNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowVehiclesError -> navigation.run {

                }
                is ShowVehiclesList -> navigation.run {
                    val adapterList = adapter.currentList.toMutableList()
                    adapterList.addAll(vehicleList)
                    adapter.submitList(adapterList)
                }
                HideLoading -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.swipeRefreshRv.isRefreshing = false
                }
                ShowLoading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.swipeRefreshRv.isRefreshing = false
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}