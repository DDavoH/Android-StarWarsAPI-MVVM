package com.davoh.starwarsapi_mvvm.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.databinding.FragmentPlanetDetailsBinding
import com.davoh.starwarsapi_mvvm.databinding.FragmentVehicleDetailsBinding


class VehicleDetailsFragment : Fragment() {

    private val args: VehicleDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentVehicleDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_vehicle_details, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.vehicle.name
        binding.vehicle = args.vehicle

        return binding.root
    }


}