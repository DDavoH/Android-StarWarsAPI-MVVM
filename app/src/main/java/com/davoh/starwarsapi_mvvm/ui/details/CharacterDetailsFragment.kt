package com.davoh.starwarsapi_mvvm.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.databinding.FragmentCharacterDetailsBinding
import com.davoh.starwarsapi_mvvm.domain.Character


class CharacterDetailsFragment : Fragment() {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentCharacterDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_character_details, container, false)

        binding.character = args.character


        return binding.root
    }
}