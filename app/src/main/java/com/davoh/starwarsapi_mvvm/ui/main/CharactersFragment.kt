package com.davoh.starwarsapi_mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.adapters.CharacterListAdapter
import com.davoh.starwarsapi_mvvm.databinding.FragmentCharactersBinding
import com.davoh.starwarsapi_mvvm.di.MainApplication
import com.davoh.starwarsapi_mvvm.di.MainComponent
import com.davoh.starwarsapi_mvvm.di.MainModule
import com.davoh.starwarsapi_mvvm.domain.Character
import com.davoh.starwarsapi_mvvm.presentation.CharactersViewModel
import com.davoh.starwarsapi_mvvm.presentation.CharactersViewModel.CharactersNavigation
import com.davoh.starwarsapi_mvvm.presentation.CharactersViewModel.CharactersNavigation.*
import com.davoh.starwarsapi_mvvm.presentation.utils.Event
import com.davoh.starwarsapi_mvvm.utils.getViewModel


class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainComponent: MainComponent
    private val charactersViewModel: CharactersViewModel by lazy {
        getViewModel { mainComponent.charactersViewModel }
    }

    private val adapter = CharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)

        mainComponent = (requireActivity().applicationContext as MainApplication).appComponent.inject(MainModule())

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        adapter.setOnItemClickListener(object : CharacterListAdapter.OnItemClickListener {
            override fun onFavoriteBtnClick(character: Character) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCharacterDetailsFragment(character))
            }
        })
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                charactersViewModel.onLoadMoreItems(
                    visibleItemCount,
                    firstVisibleItemPosition,
                    totalItemCount,
                )
            }
        })

        binding.swipeRefreshRv.setOnRefreshListener {
            charactersViewModel.onRetryGetAllCharacters()
        }

        charactersViewModel.loadCharacters()
        charactersViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        return binding.root
    }

    private fun validateEvents(event: Event<CharactersNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is ShowCharactersError -> navigation.run {
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
                }
                is ShowCharactersList -> navigation.run {
                    val listCharacters = adapter.currentList.toMutableList()
                    listCharacters.addAll(charactersList)
                    adapter.submitList(listCharacters)
                }
                HideLoading -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.swipeRefreshRv.isRefreshing = false
                }
                ShowLoading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}