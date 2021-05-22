package com.davoh.starwarsapi_mvvm.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


private val TAB_TITLES = arrayOf(
    "Characters",
    "Planets",
    "Vehicles"
)

class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CharactersFragment()
            }
            1 -> {
                PlanetsFragment()
            }
            2 -> {
                VehiclesFragment()
            }
            else ->  return CharactersFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}