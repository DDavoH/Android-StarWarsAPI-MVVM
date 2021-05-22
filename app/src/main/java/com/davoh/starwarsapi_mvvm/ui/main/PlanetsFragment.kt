package com.davoh.starwarsapi_mvvm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.adapters.PlanetListAdapter
import com.davoh.starwarsapi_mvvm.databinding.FragmentPlanetsBinding
import com.davoh.starwarsapi_mvvm.di.MainApplication
import com.davoh.starwarsapi_mvvm.di.MainComponent
import com.davoh.starwarsapi_mvvm.di.MainModule
import com.davoh.starwarsapi_mvvm.domain.Planet
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.davoh.starwarsapi_mvvm.presentation.PlanetsViewModel
import com.davoh.starwarsapi_mvvm.presentation.PlanetsViewModel.PlanetsNavigation
import com.davoh.starwarsapi_mvvm.presentation.PlanetsViewModel.PlanetsNavigation.*
import com.davoh.starwarsapi_mvvm.presentation.utils.Event
import com.davoh.starwarsapi_mvvm.utils.getViewModel

class PlanetsFragment : Fragment() {

    private var _binding: FragmentPlanetsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainComponent: MainComponent
    private val planetsViewModel: PlanetsViewModel by lazy {
        getViewModel { mainComponent.planetsViewModel }
    }
    private val adapter = PlanetListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planets, container, false)
        mainComponent = (requireActivity().applicationContext as MainApplication).appComponent.inject(
            MainModule()
        )

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        adapter.setOnItemClickListener(object : PlanetListAdapter.OnItemClickListener {
            override fun onFavoriteBtnClick(planet: Planet) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPlanetDetailsFragment(planet))
            }
        })
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                planetsViewModel.onLoadMoreItems(
                    visibleItemCount,
                    firstVisibleItemPosition,
                    totalItemCount,
                )
            }
        })

        binding.swipeRefreshRv.setOnRefreshListener {
            planetsViewModel.onRetryGetAllPlanets()
        }

        planetsViewModel.loadPlanets()
        planetsViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))


        return binding.root
    }

    private fun validateEvents(event: Event<PlanetsNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowPlanetsError -> navigation.run {

                }
                is ShowPlanetsList -> navigation.run {
                    val adapterList = adapter.currentList.toMutableList()
                    adapterList.addAll(planetsList)
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